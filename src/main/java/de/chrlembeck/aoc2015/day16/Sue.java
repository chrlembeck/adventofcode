package de.chrlembeck.aoc2015.day16;

public class Sue {

    private int nr;

    private Integer children;

    private Integer cats;

    private Integer samoyeds;

    private Integer pomeranians;

    private Integer akitas;

    private Integer vizslas;

    private Integer goldfish;

    private Integer trees;

    private Integer cars;

    private Integer perfumes;

    public Sue(String line) {
        nr = Integer.parseInt(line.substring(4, line.indexOf(':')));
        line = line.substring(line.indexOf(':') + 2);
        String[] parts = line.split(", ");
        for (String part : parts) {
            String present = part.substring(0, part.indexOf(':'));
            Integer quantity = Integer.valueOf(part.substring(part.indexOf(':') + 2));
            switch (present) {
                case "children":
                    children = quantity;
                    break;
                case "cats":
                    cats = quantity;
                    break;
                case "samoyeds":
                    samoyeds = quantity;
                    break;
                case "pomeranians":
                    pomeranians = quantity;
                    break;
                case "akitas":
                    akitas = quantity;
                    break;
                case "vizslas":
                    vizslas = quantity;
                    break;
                case "goldfish":
                    goldfish = quantity;
                    break;
                case "trees":
                    trees = quantity;
                    break;
                case "cars":
                    cars = quantity;
                    break;
                case "perfumes":
                    perfumes = quantity;
                    break;
                default:
                    throw new IllegalArgumentException(present);
            }
        }
    }

    public boolean matches(int children, int cats, int samoyeds, int pomeranians, int akitas, int vizslas, int goldfish, int trees, int cars, int perfumes) {
        return (this.children == null || (this.children == children)) &&
                (this.cats == null || (this.cats == cats)) &&
                (this.samoyeds == null || (this.samoyeds == samoyeds)) &&
                (this.pomeranians == null || (this.pomeranians == pomeranians)) &&
                (this.akitas == null || (this.akitas == akitas)) &&
                (this.vizslas == null || (this.vizslas == vizslas)) &&
                (this.goldfish == null || (this.goldfish == goldfish)) &&
                (this.trees == null || (this.trees == trees)) &&
                (this.cars == null || (this.cars == cars)) &&
                (this.perfumes == null || (this.perfumes == perfumes));
    }

    public boolean matches2(int children, int cats, int samoyeds, int pomeranians, int akitas, int vizslas, int goldfish, int trees, int cars, int perfumes) {
        return (this.children == null || (this.children == children)) &&
                (this.cats == null || (this.cats > cats)) &&
                (this.samoyeds == null || (this.samoyeds == samoyeds)) &&
                (this.pomeranians == null || (this.pomeranians < pomeranians)) &&
                (this.akitas == null || (this.akitas == akitas)) &&
                (this.vizslas == null || (this.vizslas == vizslas)) &&
                (this.goldfish == null || (this.goldfish < goldfish)) &&
                (this.trees == null || (this.trees > trees)) &&
                (this.cars == null || (this.cars == cars)) &&
                (this.perfumes == null || (this.perfumes == perfumes));
    }

    public int getNr() {
        return nr;
    }
}
