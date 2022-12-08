package de.chrlembeck.aoc2021.day19;

import de.chrlembeck.aoccommon.AbstractAocBase;
import de.chrlembeck.util.lang.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Aoc2021Day19 extends AbstractAocBase {

    private final Pattern scannerPattern = Pattern.compile("--- scanner (\\d+) ---");

    private final Pattern vectorPattern = Pattern.compile("(-?\\d+),(-?\\d+),(-?\\d+)");

    public static void main(final String[] args) {
        new Aoc2021Day19().run();
    }

    @Override
    public Integer part1(final Scanner input) {
        List<BeaconScanner> fixedScanners = orginizeScanners(input);
        Set<Vector> result = new HashSet<>();
        fixedScanners.forEach(s -> result.addAll(s.getFixedBeacons()));
        return result.size();
    }

    @Override
    public Integer part2(final Scanner input) {
        List<BeaconScanner> fixedScanners = orginizeScanners(input);
        int maxDist = 0;
        for (BeaconScanner first: fixedScanners) {
            for (BeaconScanner second: fixedScanners) {
                maxDist = Math.max(maxDist, first.getFixedPosition().distance(second.getFixedPosition()));
            }
        }
        return maxDist;
    }

    public List<BeaconScanner> orginizeScanners(final Scanner input) {
        List<BeaconScanner> scanners = new ArrayList<>();
        while (input.hasNext()) {
            scanners.add(parseScanner(input));
        }

        List<BeaconScanner> fixedScanners = new ArrayList<>();
        BeaconScanner initialScanner = scanners.remove(0);
        initialScanner.fix(new Vector(0, 0, 0), 0);
        fixedScanners.add(initialScanner);
        for (int compareIndex = 0; !scanners.isEmpty(); compareIndex++) {
            BeaconScanner fixedScanner = fixedScanners.get(compareIndex);
            for (int scannerIdx = scanners.size() - 1; scannerIdx >= 0; scannerIdx--) {
                BeaconScanner scanner = scanners.get(scannerIdx);
                compareScanners(fixedScanner, scanner);
                if (scanner.isFixed()) {
                    fixedScanners.add(scanner);
                    scanners.remove(scannerIdx);
                }

            }
        }
        return fixedScanners;
    }

    private void compareScanners(BeaconScanner fixedScanner, BeaconScanner scanner) {
        Set<Vector> fixedBeacons = fixedScanner.getFixedBeacons();
        for (int direction = 0; direction < 24; direction++) {
            for (Vector fixedCompareBeacon : fixedBeacons) {
                Set<Vector> testBeacons = scanner.getBeacons(direction);
                for (Vector freeCompareBeacon : testBeacons) {
                    Vector translateVector = new Vector(
                            fixedCompareBeacon.x() - freeCompareBeacon.x(),
                            fixedCompareBeacon.y() - freeCompareBeacon.y(),
                            fixedCompareBeacon.z() - freeCompareBeacon.z());
                    int counter = 0;
                    for (Vector testBeacon : testBeacons) {
                        if (fixedBeacons.contains(testBeacon.translate(translateVector))) {
                            counter++;
                        }
                    }
                    if (counter >= 12) {
                        scanner.fix(translateVector, direction);
                        return;
                    }
                }
            }
        }
    }

    private BeaconScanner parseScanner(final Scanner input) {
        int id = Integer.parseInt(matchRegex(scannerPattern, input.nextLine()).group(1));
        String line;
        BeaconScanner scanner = new BeaconScanner(id);
        while (input.hasNextLine() && !StringUtils.isEmpty(line = input.nextLine())) {
            Matcher matcher = matchRegex(vectorPattern, line);
            scanner.addBeacon(new Vector(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3))));
        }
        return scanner;
    }

    @Override
    public String getInputLocation(final int part) {
        return "/input/aoc2021/aoc2021day19.txt";
    }
}