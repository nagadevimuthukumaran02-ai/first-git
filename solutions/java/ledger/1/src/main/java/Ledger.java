import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Ledger {
    public LedgerEntry createLedgerEntry(String d, String desc, int c) {
        LedgerEntry le = new LedgerEntry();
        le.setChange(c);
        le.setDescription(desc);
        le.setLocalDate(LocalDate.parse(d));
        return le;
    }

    public String format(String cur, String loc, LedgerEntry[] entries) {
        String header;
        String curSymb;
        String datPat;
        String decSep;
        String thSep;

        if (!cur.equals("USD") && !cur.equals("EUR")) {
            throw new IllegalArgumentException("Invalid currency");
        } else if (!loc.equals("en-US") && !loc.equals("nl-NL")) {
            throw new IllegalArgumentException("Invalid locale");
        }

        curSymb = cur.equals("USD") ? "$" : "€";

        if (loc.equals("en-US")) {
            datPat = "MM/dd/yyyy";
            decSep = ".";
            thSep = ",";
            header = "Date       | Description               | Change       ";
        } else {
            datPat = "dd/MM/yyyy";
            decSep = ",";
            thSep = ".";
            header = "Datum      | Omschrijving              | Verandering  ";
        }

        StringBuilder s = new StringBuilder(header);

        if (entries.length > 0) {
            List<LedgerEntry> all = new ArrayList<>(List.of(entries));
            all.sort((o1, o2) -> {
                int cmp = o1.getLocalDate().compareTo(o2.getLocalDate());
                if (cmp != 0) return cmp;
                cmp = o1.getDescription().compareTo(o2.getDescription());
                if (cmp != 0) return cmp;
                return Double.compare(o1.getChange(), o2.getChange());
            });

            for (LedgerEntry e : all) {
                String date = e.getLocalDate().format(DateTimeFormatter.ofPattern(datPat));

                String desc = e.getDescription();
                if (desc.length() > 25) {
                    desc = desc.substring(0, 22) + "...";
                }

                double absValue = Math.abs(e.getChange()) / 100.0;
                String converted = String.format(java.util.Locale.US, "%.02f", absValue);

                String[] parts = converted.split("\\.");
                String integerPart = "";
                int count = 1;
                for (int ind = parts[0].length() - 1; ind >= 0; ind--) {
                    if ((count % 3 == 0) && ind > 0) {
                        integerPart = thSep + parts[0].charAt(ind) + integerPart;
                    } else {
                        integerPart = parts[0].charAt(ind) + integerPart;
                    }
                    count++;
                }

                String numberOnly = integerPart + decSep + parts[1];
                String amount;

                boolean negative = e.getChange() < 0;
                if (loc.equals("en-US")) {
                    amount = curSymb + numberOnly;
                    amount = negative ? "(" + amount + ")" : amount + " ";
                } else {
                    amount = negative
                            ? curSymb + " -" + numberOnly + " "
                            : curSymb + " " + numberOnly + " ";
                }

                s.append("\n");
                s.append(String.format("%s | %-25s | %13s", date, desc, amount));
            }
        }

        return s.toString();
    }

    public static class LedgerEntry {
        LocalDate localDate;
        String description;
        double change;

        public LocalDate getLocalDate() {
            return localDate;
        }

        public void setLocalDate(LocalDate localDate) {
            this.localDate = localDate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getChange() {
            return change;
        }

        public void setChange(double change) {
            this.change = change;
        }
    }
}