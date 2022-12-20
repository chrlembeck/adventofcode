package de.chrlembeck.aoc2021.day23;


/**
 * <pre>
 *     #####################################
 *     #                                   #
 *     # H0 H1 H2 H3 H4 H5 H6 H7 H8 H9 H10 #
 *     #                                   #
 *     ####### A1 ## B1 ## C1 ## D1 ########
 *           # A2 ## B2 ## C2 ## D2 #
 *           # A3 ## B3 ## C3 ## D3 #
 *           # A4 ## B4 ## C4 ## D4 #
 *           ########################
 * </pre>
 */
public enum Position {

    H0, H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, A1, A2, A3, A4, B1, B2, B3, B4, C1, C2, C3, C4, D1, D2, D3, D4;

    public Position left() {
        return switch (this) {
            case H1 -> H0;
            case H2 -> H1;
            case H3 -> H2;
            case H4 -> H3;
            case H5 -> H4;
            case H6 -> H5;
            case H7 -> H6;
            case H8 -> H7;
            case H9 -> H8;
            case H10 -> H9;
            default -> null;
        };
    }

    public Position right() {
        return switch (this) {
            case H0 -> H1;
            case H1 -> H2;
            case H2 -> H3;
            case H3 -> H4;
            case H4 -> H5;
            case H5 -> H6;
            case H6 -> H7;
            case H7 -> H8;
            case H8 -> H9;
            case H9 -> H10;
            default -> null;
        };
    }

    public Position up() {
        return switch (this) {
            case A2 -> A1;
            case B2 -> B1;
            case C2 -> C1;
            case D2 -> D1;
            case A1 -> H2;
            case B1 -> H4;
            case C1 -> H6;
            case D1 -> H8;
            case A3 -> A2;
            case B3 -> B2;
            case C3 -> C2;
            case D3 -> D2;
            case A4 -> A3;
            case B4 -> B3;
            case C4 -> C3;
            case D4 -> D3;
            default -> null;
        };
    }

    public Position down(boolean step2) {
        return switch (this) {
            case A1 -> A2;
            case B1 -> B2;
            case C1 -> C2;
            case D1 -> D2;
            case H2 -> A1;
            case H4 -> B1;
            case H6 -> C1;
            case H8 -> D1;
            case A2 -> step2 ? A3 : null;
            case B2 -> step2 ? B3 : null;
            case C2 -> step2 ? C3 : null;
            case D2 -> step2 ? D3 : null;
            case A3 -> A4;
            case B3 -> B4;
            case C3 -> C4;
            case D3 -> D4;
            default -> null;
        };
    }

    public boolean isRoom() {
        return switch (this) {
            case A2, A1, B2, B1, C2, C1, D2, D1, A3, A4, B3, B4, C3, C4, D3, D4 -> true;
            default -> false;
        };
    }

    public boolean isValid() {
        return switch (this) {
            case H2, H4, H6, H8 -> false;
            default -> true;
        };
    }
}