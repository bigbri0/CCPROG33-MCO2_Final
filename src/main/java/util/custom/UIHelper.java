package util.custom;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.ArrayList;

public class UIHelper {
        public static Color colorPrimary = Color.decode("#69dbae");
        public static Color colorOnPrimary = Color.decode("#003827");
        public static Color colorPrimaryContainer = Color.decode("#00513a");
        public static Color colorOnPrimaryContainer = Color.decode("#87f8c9");

        public static Color colorSecondary = Color.decode("#b3ccbe");
        public static Color colorOnSecondary = Color.decode("#1f352b");
        public static Color colorSecondaryContainer = Color.decode("#354b41");
        public static Color colorOnSecondaryContainer = Color.decode("#cfe9da");

        public static Color colorTertiary = Color.decode("#a6ccdf");
        public static Color colorOnTertiary = Color.decode("#083544");
        public static Color colorTertiaryContainer = Color.decode("#254b5b");
        public static Color colorOnTertiaryContainer = Color.decode("#c1e8fc");

        public static Color colorQuaternary = Color.decode("#D0BCFF");
        public static Color colorOnQuaternary = Color.decode("#381E72");
        public static Color colorQuaternaryContainer = Color.decode("#4F378B");
        public static Color colorOnQuaternaryContainer = Color.decode("#EADDFF");

        public static Color colorQuinary = Color.decode("#ffb4ab");
        public static Color colorOnQuinary = Color.decode("#690005");
        public static Color colorQuinaryContainer = Color.decode("#93000a");
        public static Color colorOnQuinaryContainer = Color.decode("#ffdad6");

        public static Color colorSenary = Color.decode("#fbaaff");
        public static Color colorOnSenary = Color.decode("#580064");
        public static Color colorSenaryContainer = Color.decode("#7c028d");
        public static Color colorOnSenaryContainer = Color.decode("#ffd6fc");

        public static Color colorBackground = Color.decode("#191c1a");
        public static Color colorOnBackground = Color.decode("#e1e3df");
        public static Color colorSurface = Color.decode("#191c1a");
        public static Color colorOnSurface = Color.decode("#e1e3df");
        public static Color colorSurfaceVariant = Color.decode("#404944");
        public static Color colorOnSurfaceVariant = Color.decode("#404944");
        public static Color colorOutline = Color.decode("#89938d");

        public static List<List<List<String>>> mapData = new ArrayList<>(List.of(
                        new ArrayList<>(List.of(
                                        new ArrayList<>(List.of("1", "D0,1", "1",
                                                        "SE", "1", "SE",
                                                        "1", "1", "1",
                                                        "1", "1", "1",
                                                        "1", "1", "1",
                                                        "1", "1", "1",
                                                        "1", "F0,0", "1")),
                                        new ArrayList<>(List.of("1", "1", "1", "D1,2", "1", "1", "1",
                                                        "1", "1", "1", "SE", "1", "1", "1",
                                                        "1", "1", "1", "1", "1", "1", "1",
                                                        "SE", "1", "SE", "SE", "SE", "1", "SE",
                                                        "1", "1", "1", "1", "1", "1", "1",
                                                        "1", "1", "SE", "1", "SE", "1", "1",
                                                        "1", "1", "1", "D1,0", "1", "1", "1")),
                                        new ArrayList<>(List.of("1", "1", "F0,1", "1", "1",
                                                        "1", "1", "1", "1", "1",
                                                        "1", "1", "1", "1", "1",
                                                        "1", "1", "BE", "1", "1",
                                                        "1", "1", "1", "1", "1",
                                                        "1", "1", "1", "1", "1",
                                                        "1", "1", "D2,1", "1", "1")))),
                        new ArrayList<>(List.of(
                                        new ArrayList<>(List.of("1", "1", "F1,0", "1", "1",
                                                        "1", "1", "1", "1", "1",
                                                        "1", "1", "1", "1", "1",
                                                        "1", "SE", "1", "SE", "1",
                                                        "1", "1", "D0,1", "1", "1")),
                                        new ArrayList<>(List.of("1", "D1,0", "1",
                                                        "SE", "1", "1",
                                                        "1", "1", "1",
                                                        "SE", "1", "D1,2",
                                                        "1", "1", "1",
                                                        "SE", "1", "SE",
                                                        "1", "1", "1")),
                                        new ArrayList<>(List.of("0", "1", "D2,4", "1", "0",
                                                        "0", "1", "SE", "1", "0",
                                                        "1", "1", "1", "1", "1",
                                                        "D2,1", "1", "1", "1", "D2,3",
                                                        "1", "1", "1", "1", "1",
                                                        "0", "1", "SE", "1", "0",
                                                        "0", "1", "1", "1", "0")),
                                        new ArrayList<>(List.of("1", "1", "SE", "1", "SE", "1",
                                                        "D3,2", "1", "1", "1", "1", "1",
                                                        "1", "1", "SE", "1", "SE", "1")),
                                        new ArrayList<>(List.of("0", "0", "1", "F1,2", "1", "0", "0",
                                                        "1", "1", "1", "1", "1", "1", "1",
                                                        "1", "SE", "1", "SE", "1", "SE", "1",
                                                        "1", "1", "1", "1", "1", "1", "1",
                                                        "1", "SE", "1", "BE", "1", "SE", "1",
                                                        "1", "1", "1", "1", "1", "1", "1",
                                                        "1", "SE", "1", "1", "1", "SE", "1",
                                                        "1", "1", "1", "D4,2", "1", "1", "1")))),
                        new ArrayList<>(List.of(
                                        new ArrayList<>(List.of("1", "D0,1", "1",
                                                        "1", "1", "1",
                                                        "1", "1", "1",
                                                        "1", "1", "1",
                                                        "1", "1", "1",
                                                        "1", "1", "1",
                                                        "1", "1", "1",
                                                        "1", "1", "1",
                                                        "1", "F2,1", "1")),
                                        new ArrayList<>(List.of("0", "1", "1", "D1,2", "1", "1", "0",
                                                        "1", "1", "1", "1", "1", "1", "1",
                                                        "1", "1", "1", "1", "1", "1", "1",
                                                        "1", "1", "1", "BE", "1", "1", "1",
                                                        "1", "1", "1", "1", "1", "1", "1",
                                                        "1", "1", "1", "1", "1", "1", "1",
                                                        "0", "1", "1", "D1,0", "1", "1", "0")),
                                        new ArrayList<>(List.of("1", "C", "1",
                                                        "1", "1", "1",
                                                        "1", "1", "1",
                                                        "1", "1", "1",
                                                        "1", "1", "1",
                                                        "1", "1", "1",
                                                        "1", "1", "1",
                                                        "1", "1", "1",
                                                        "1", "D2,1", "1"))))));

        public static List<List<Dimension>> mapDataDimensions = new ArrayList<>(List.of(
                        new ArrayList<>(List.of(new Dimension(3, 7), new Dimension(7, 7), new Dimension(5, 7))),
                        new ArrayList<>(List.of(new Dimension(5, 5), new Dimension(3, 7), new Dimension(5, 7),
                                        new Dimension(3, 6), new Dimension(7, 8))),
                        new ArrayList<>(List.of(new Dimension(3, 9), new Dimension(7, 7), new Dimension(3, 9)))));

}
