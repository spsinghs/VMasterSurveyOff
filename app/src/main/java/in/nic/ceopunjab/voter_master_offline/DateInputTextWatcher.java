package in.nic.ceopunjab.voter_master_offline;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by admin on 05/19/2017.
 */

public class DateInputTextWatcher implements TextWatcher {

    public static final String DATE_SEPARATOR = "/";

    private DateFormatter dateFormatter;
    private EditText editText;
    private String prevText;

    public DateInputTextWatcher(EditText editText) {
        this.editText = editText;
        dateFormatter = new DateFormatter();
    }

    @Override
    public void afterTextChanged(Editable text) {
        int prevLength = prevText == null ? 0 : prevText.length();
        int newLength = text == null ? 0 : text.length();
        boolean deleted = newLength < prevLength;

        // Don't try to format if the user is deleting text. Auto-fixing the input
        // date while the user is already trying to possibly fix a mistake would
        // be terrible.
        if (!deleted && text != null) {
            int cursorPosition = editText.getSelectionStart();
            boolean shouldAddMissingZeroes = cursorPosition == text.length();
            String parsedDate = dateFormatter.parse(text.toString(), shouldAddMissingZeroes);

            // Remove all listeners on the field (which includes DateInputTextWatcher)
            // before modifying the text to avoid going to infinite loop.
            editText.removeTextChangedListener(this);
            text.replace(0, text.length(), parsedDate);
            editText.addTextChangedListener(this);
        }

        prevText = text == null ? null : text.toString();
    }

    /**
     * This might not be the best formatter, but I had fun implementing this. Instead of working on the date-to-format
     * as a single entity, it considers it as a combination of three separate parts: DD, MM and YYYY.
     */
    private static class DateFormatter {

        private static final char DATE_SEPARATOR_CHAR = '/';
        private static final int MAX_BATCHES = 3;
        private static final int MAX_LENGTH_YEAR = 4;

        private String fullText = "";
        private String currentPart = "";
        private int partCount = 0;
        private boolean canAddMissingZeroes;

        /**
         * @param dateString          The date to format.
         * @param canAddMissingZeroes When enabled, a 0 will automatically get added if the user fills a single digit in
         *                            day or month field. That is, 12/4/____ will become 12/04/____. It's recommended to
         *                            enable this only when the user is appending characters at the end. That is, the
         *                            cursor is at the end or be prepared to see strange auto-formatting.
         */
        public String parse(String dateString, boolean canAddMissingZeroes) {
            this.canAddMissingZeroes = canAddMissingZeroes;
            resetState();

            // Construct the formatted date
            for (char aChar : dateString.toCharArray()) {
                addNextChar(aChar);
            }
            endConstruction();

            return fullText;
        }

        private DateFormatter addNextChar(char nextChar) {
            // Plan:
            // YYYY: Let the year digits pass till 4 digits
            // DD and MM:
            //  1. When "/" is encountered -> add a leading 0 if required -> dispatch this batch
            //  2. When length(batch) == 2 digits -> add "\" -> dispatch this batch

            if (partCount == MAX_BATCHES - 1) {
                // This is the YYYY part. No formatting is required here.
                if (currentPart.length() >= MAX_LENGTH_YEAR || nextChar == DATE_SEPARATOR_CHAR) {
                    // The date is complete. The user is probably entering more than 4 digits
                    // for the year. Ignore.
                    return this;
                }
                currentPart += nextChar;
                fullText += nextChar;

            } else {
                // Inside either DD or MM part
                if (nextChar == DATE_SEPARATOR_CHAR) {
                    // Don't accept successive '/'s
                    if (currentPart.length() == 0 || currentPart.contains(DATE_SEPARATOR)) {
                        return this;
                    }

                    // Add a leading 0 if required + allowed
                    if (canAddMissingZeroes && currentPart.length() == 1) {
                        currentPart = "0" + currentPart;
                    }
                }

                // Append this new char
                currentPart += nextChar;

                // And a slash if the user has already entered 2 digits. At this point, a
                // 2-digit-strong part will not already have any slashes.
                if (currentPart.length() == 2 && !currentPart.contains(DATE_SEPARATOR)) {
                    currentPart += DATE_SEPARATOR_CHAR;
                }

                // Mark this part as complete if possible
                if (currentPart.length() == 3) {
                    fullText += currentPart;
                    currentPart = "";
                    partCount++;
                }
            }

            return this;
        }

        void endConstruction() {
            // Append the left-over part
            if (partCount != MAX_BATCHES - 1) {
                fullText += currentPart;
            }
        }

        void resetState() {
            partCount = 0;
            fullText = "";
            currentPart = "";
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

}

