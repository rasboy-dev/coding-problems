package yandexcup;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ClayStatistics {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        Stats stats = new Stats();
        boolean terminated = false;

        while (!terminated) {
            String queryStr = reader.readLine();
            String[] query = queryStr.split(" ");
            String queryType = query[0];
            if ("+".equals(queryType)) {
                stats.addNewPatient(parseInt(query[1]), parseDouble(query[2]));
            } else if ("-".equals(queryType)) {
                stats.releasePatient(parseInt(query[1]));
            } else if ("~".equals(queryType)) {
                stats.updateTemp(parseInt(query[1]), parseDouble(query[2]));
            } else if ("?".equals(queryType)) {
                BigDecimal avg = stats.getAverage().setScale(9, RoundingMode.HALF_UP).stripTrailingZeros();
                if (avg.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO)) {
                    avg = avg.movePointRight(1).movePointLeft(1);
                }
                System.out.println(avg);
                System.out.flush();
            } else if ("!".equals(queryType)) {
                terminated = true;
            }
        }

        reader.close();
        writer.close();
    }

    private static class Stats {

        private double tempSum = 0;

        private final Map<Integer, Double> patients = new HashMap<>();

        public void addNewPatient(int id, double temp) {
            patients.put(id, temp);
            tempSum += temp;
        }

        public void releasePatient(int id) {
            tempSum -= patients.get(id);
            patients.remove(id);
        }

        public void updateTemp(int id, double newTemp) {
            tempSum -= patients.remove(id);
            tempSum += newTemp;

            patients.put(id, newTemp);
        }

        public BigDecimal getAverage() {
            return BigDecimal.valueOf(tempSum).setScale(9, RoundingMode.UP).divide(BigDecimal.valueOf(patients.size()), RoundingMode.HALF_UP);
        }
    }
}


//import java.io.*;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.HashMap;
//import java.util.Map;
//
//import static java.lang.Double.parseDouble;
//import static java.lang.Integer.parseInt;
//
//public class ClayStatistics {
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        Stats stats = new Stats();
//        boolean terminated = false;
//
//        while (!terminated) {
//            String queryStr = reader.readLine();
//            String[] query = queryStr.split(" ");
//            String queryType = query[0];
//            if ("+".equals(queryType)) {
//                stats.addNewPatient(parseInt(query[1]), parseDouble(query[2]));
//            } else if ("-".equals(queryType)) {
//                stats.releasePatient(parseInt(query[1]));
//            } else if ("~".equals(queryType)) {
//                stats.updateTemp(parseInt(query[1]), parseDouble(query[2]));
//            } else if ("?".equals(queryType)) {
//                BigDecimal avg = stats.getAverage().setScale(9, RoundingMode.HALF_UP).stripTrailingZeros();
//                if (avg.remainder(BigDecimal.ONE).equals(BigDecimal.ZERO)) {
//                    avg = avg.movePointRight(1).movePointLeft(1);
//                }
//                writer.write(avg + "\n");
//                writer.flush();
//            } else if ("!".equals(queryType)) {
//                terminated = true;
//            }
//        }
//
//        reader.close();
//        writer.close();
//    }
//
//    private static class Stats {
//
//        private double tempSum = 0;
//
//        private final Map<Integer, Double> patients = new HashMap<>();
//
//        private final Map<Integer, Double> buffer = new HashMap<>();
//
//        public void addNewPatient(int id, double temp) {
////            patients.put(id, temp);
////            tempSum += temp;
//            if (patients.containsKey(id)) {
//                buffer.put(id, temp - patients.get(id));
//            } else {
//                buffer.put(id, temp);
//            }
//        }
//
//        public void releasePatient(int id) {
////            tempSum -= patients.get(id);
////            patients.remove(id);
//            if (patients.containsKey(id)) {
//                buffer.put(id, -200.0);
//            } else {
//                buffer.remove(id);
//            }
//        }
//
//        public void updateTemp(int id, double newTemp) {
////            tempSum -= patients.remove(id);
////            tempSum += newTemp;
////            patients.put(id, newTemp);
//            if (patients.containsKey(id)) {
//                buffer.put(id, newTemp - patients.get(id));
//            } else {
//                buffer.put(id, newTemp);
//            }
//        }
//
//        public BigDecimal getAverage() {
//            for (Map.Entry<Integer, Double> entry : buffer.entrySet()) {
//                Integer id = entry.getKey();
//                Double temp = entry.getValue();
//
//                if (patients.containsKey(id) && temp < -100) {
//                    tempSum -= patients.remove(id);
//                    continue;
//                }
//
//                if (patients.containsKey(id)) {
//                    patients.put(id, patients.get(id) + temp);
//                } else {
//                    patients.put(id, temp);
//                }
//                tempSum += temp;
//            }
//            buffer.clear();
//            return BigDecimal.valueOf(tempSum).setScale(9, RoundingMode.UP).divide(BigDecimal.valueOf(patients.size()), RoundingMode.HALF_UP);
//        }
//    }
//}
//
