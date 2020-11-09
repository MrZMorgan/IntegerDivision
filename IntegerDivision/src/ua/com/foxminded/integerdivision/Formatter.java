package ua.com.foxminded.integerdivision;

import java.util.ArrayList;
import java.util.List;

public class Formatter {
    List<Integer> resultTmp = new ArrayList<>();
    List<String> formattedResult = new ArrayList<>();

    private int spacesBefore = 0;
    private int spacesAfter = 0;
    private int maxLength;

    private static final String VERTICAL_SEPARATOR = "|";
    private static final String HORIZONTAL_SEPARATOR = "-";
    private static final String MINUS = "_";
    private static final String SPACE = " ";


    private void createResultTmp(CalculatorDTO dto) {
        this.maxLength = String.valueOf(dto.getDividend()).length() + 1;
        int numberOfStrings = dto.getDividendsTMP().size();

        for (int i = 0; i < numberOfStrings; i++) {
            resultTmp.add(dto.getDividendsTMP().get(i));
            resultTmp.add(dto.getDividersTMP().get(i));
        }
    }

    private void formatResult(CalculatorDTO dto) {
        for (int i = 0; i < resultTmp.size(); i++) {
            if (i % 2 == 0) {
                addDividendLine(i, dto);
            } else {
                addDividerLine(i, dto);
                addSeparator(resultTmp.get(i).toString().length(), spacesBefore);
            }
        }
        addResultValueOfLongDivision(dto);
        formatFirstLine(dto);
        formatSecondLine(dto);
        formatThirdLine(dto);
    }

    private void addDividendLine(int i, CalculatorDTO dto) {
        StringBuilder line = new StringBuilder();
        spacesBefore = dto.getZerosBeforeDividend().get(i / 2);

        addSpacesBefore(line, spacesBefore);
        line.append(MINUS);
        line.append(resultTmp.get(i));

        spacesAfter = maxLength - line.toString().length();
        addSpacesAfter(line, spacesAfter);

        formattedResult.add(line.toString());
    }

    private void addDividerLine(int i, CalculatorDTO dto) {
        StringBuilder line = new StringBuilder();
        spacesBefore = dto.getZerosBeforeDivider().get(i / 2);

        addSpacesBefore(line, spacesBefore);
        line.append(resultTmp.get(i));

        spacesAfter = maxLength - line.toString().length();
        addSpacesAfter(line, spacesAfter);

        formattedResult.add(line.toString());
    }

    private void addSeparator(int separatorLength, int spacesBefore) {
        StringBuilder separator = new StringBuilder();

        for (int i = 0; i < spacesBefore; i++) {
            separator.append(SPACE);
        }

        for (int i = 0; i < separatorLength; i++) {
            separator.append(HORIZONTAL_SEPARATOR);
        }

        formattedResult.add(separator.toString());
    }

    private void addResultValueOfLongDivision(CalculatorDTO dto) {
        StringBuilder lastLineOfResult = new StringBuilder();
        int remainderLength = String.valueOf(dto.getRemainder()).length();
        int spacesBefore = maxLength - remainderLength;

        for (int i = 0; i < spacesBefore; i++) {
            lastLineOfResult.append(SPACE);
        }

        lastLineOfResult.append(dto.getRemainder());
        formattedResult.add(lastLineOfResult.toString());
    }

    private void formatFirstLine(CalculatorDTO dto) {
        String line = MINUS + dto.getDividend() + VERTICAL_SEPARATOR + dto.getDivider();
        formattedResult.set(0, line);
    }

    private void formatSecondLine(CalculatorDTO dto) {
        StringBuilder line = new StringBuilder(formattedResult.get(1));
        int resultOfLongDivisionLength = String.valueOf(dto.getResult()).length();
        for (int i = 0; i < resultOfLongDivisionLength + 1; i++) {
            line.append(HORIZONTAL_SEPARATOR);
        }
        formattedResult.set(1, line.toString());
    }

    private void formatThirdLine(CalculatorDTO dto) {
        StringBuilder line = new StringBuilder(formattedResult.get(2));
        int spacesAfter = maxLength - line.toString().length();
        for (int i = 0; i < spacesAfter; i++) {
            line.append(SPACE);
        }
        line.append(VERTICAL_SEPARATOR);
        line.append(dto.getResult());
        formattedResult.set(2, line.toString());
    }

    private void addSpacesBefore(StringBuilder line, int spacesBefore) {
        for (int j = 0; j < spacesBefore; j++) {
            line.append(SPACE);
        }
    }

    private void addSpacesAfter(StringBuilder line, int spacesAfter) {
        for (int j = 0; j < spacesAfter; j++) {
            line.append(SPACE);
        }
    }

    public String createResult(CalculatorDTO dto) {
        StringBuilder result = new StringBuilder();

        if (dto.getDividend() < dto.getDivider()) {
            result.append(dto.getDividend() + " / " + dto.getDivider() + " = 0");
        } else {
            createResultTmp(dto);
            formatResult(dto);
            for (String s : formattedResult) {
                result.append(s + "\n");
            }
        }

        return result.toString();
    }
}