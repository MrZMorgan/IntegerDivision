package ua.com.foxminded.integerdivision;

import java.util.ArrayList;
import java.util.List;

public class Formatter {
    CalculatorDTO dto;

    List<Integer> resultTmp = new ArrayList<>();
    List<String> result = new ArrayList<>();

    private int spacesBefore = 0;
    private int spacesAfter = 0;
    private int maxLength;

    private final static String VERTICAL_SEPARATOR = "|";
    private final static String HORIZONTAL_SEPARATOR = "-";
    private final static String MINUS = "_";
    private final static String SPACE = " ";

    public Formatter(CalculatorDTO dto) {
        this.dto = dto;
        this.maxLength = String.valueOf(dto.getDividend()).length() + 1;
    }

    private void createResultTmp() {
        int numberOfStrings = dto.getDividendsTMP().size();

        for (int i = 0; i < numberOfStrings; i++) {
            resultTmp.add(dto.getDividendsTMP().get(i));
            resultTmp.add(dto.getDividersTMP().get(i));
        }
    }

    private void createResult() {
        for (int i = 0; i < resultTmp.size(); i++) {
            if (i % 2 == 0) {
                addDividendLine(i);
            } else {
                addDividerLine(i);
                addSeparator(resultTmp.get(i).toString().length(), spacesBefore);
            }
        }
        addResultValueOfLongDivision();
        formatFirstLine();
        formatSecondLine();
        formatThirdLine();
    }

    private void addDividendLine(int i) {
        StringBuilder line = new StringBuilder();
        spacesBefore = dto.getZerosBeforeDividend().get(i / 2);

        addSpacesBefore(line, spacesBefore);
        line.append(MINUS);
        line.append(resultTmp.get(i));

        spacesAfter = maxLength - line.toString().length();
        addSpacesAfter(line, spacesAfter);

        result.add(line.toString());
    }

    private void addDividerLine(int i) {
        StringBuilder line = new StringBuilder();
        spacesBefore = dto.getZerosBeforeDivider().get(i / 2);

        addSpacesBefore(line, spacesBefore);
        line.append(resultTmp.get(i));

        spacesAfter = maxLength - line.toString().length();
        addSpacesAfter(line, spacesAfter);

        result.add(line.toString());
    }

    private void addSeparator(int separatorLength, int spacesBefore) {
        StringBuilder separator = new StringBuilder();

        for (int i = 0; i < spacesBefore; i++) {
            separator.append(SPACE);
        }

        for (int i = 0; i < separatorLength; i++) {
            separator.append(HORIZONTAL_SEPARATOR);
        }

        result.add(separator.toString());
    }

    private void addResultValueOfLongDivision() {
        StringBuilder lastLineOfResult = new StringBuilder();
        int remainderLength = String.valueOf(dto.getRemainder()).length();
        int spacesBefore = maxLength - remainderLength;

        for (int i = 0; i < spacesBefore; i++) {
            lastLineOfResult.append(SPACE);
        }

        lastLineOfResult.append(dto.getRemainder());
        result.add(lastLineOfResult.toString());
    }

    private void formatFirstLine() {
        String line = MINUS + dto.getDividend() + VERTICAL_SEPARATOR + dto.getDivider();
        result.set(0, line);
    }

    private void formatSecondLine() {
        StringBuilder line = new StringBuilder(result.get(1));
        int resultOfLongDivisionLength = String.valueOf(dto.getResult()).length();
        for (int i = 0; i < resultOfLongDivisionLength + 1; i++) {
            line.append(HORIZONTAL_SEPARATOR);
        }
        result.set(1, line.toString());
    }

    private void formatThirdLine() {
        StringBuilder line = new StringBuilder(result.get(2));
        int spacesAfter = maxLength - line.toString().length();
        for (int i = 0; i < spacesAfter; i++) {
            line.append(SPACE);
        }
        line.append(VERTICAL_SEPARATOR);
        line.append(dto.getResult());
        result.set(2, line.toString());
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

    public void printResult() {
        if (dto.getDividend() < dto.getDivider()) {
            System.out.println(dto.getDividend() + " / " + dto.getDivider() + " = 0");;
        } else {
            createResultTmp();
            createResult();

            for (String s : result) {
                System.out.println(s);
            }
        }
    }
}
