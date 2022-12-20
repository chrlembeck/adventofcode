package de.chrlembeck.aoc2021.day23;


/**
 * <pre>
 *     #####################################
 *     #                                   #
 *     # H0 H1 H2 H3 H4 H5 H6 H7 H8 H9 H10 #
 *     #                                   #
 *     ####### A2 ## B2 ## C2 ## D2 ########
 *           #    ##    ##    ##    #
 *           # A1 ## B1 ## C1 ## D1 #
 *           #    ##    ##    ##    #
 *           ########################
 * </pre>
 */
public enum Position {

    H0, H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, A1, A2, B1, B2, C1, C2, D1, D2;

    Position() {
    }

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
            case A1 -> A2;
            case B1 -> B2;
            case C1 -> C2;
            case D1 -> D2;
            case A2 -> H2;
            case B2 -> H4;
            case C2 -> H6;
            case D2 -> H8;
            default -> null;
        };
    }

    public Position down() {
        return switch (this) {
            case A2 -> A1;
            case B2 -> B1;
            case C2 -> C1;
            case D2 -> D1;
            case H2 -> A2;
            case H4 -> B2;
            case H6 -> C2;
            case H8 -> D2;
            default -> null;
        };
    }

    public boolean isRoom() {
        return switch (this) {
            case A1, A2, B1, B2, C1, C2, D1, D2 -> true;
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