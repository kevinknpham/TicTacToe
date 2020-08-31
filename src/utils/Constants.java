package utils;

public class Constants {
    public enum Color {
        RESET("\033[0m"),
        RED("\033[1;31m"),
        BLUE("\033[1;34m");

        private final String code;

        Color(String code) {
            this.code = code;
        }

        @Override
        public String toString() {
            return code;
        }
    }

    public enum TileState {
        BLANK(" ", Color.RESET),
        X("X", Color.RED),
        O("O", Color.BLUE),
        DRAW("", Color.RESET);

        private final String rep;
        private final Color color;

        TileState(String rep, Color color) {
            this.rep = rep;
            this.color = color;
        }

        @Override
        public String toString() {
            return this.color + this.rep + Color.RESET;
        }
    }
}
