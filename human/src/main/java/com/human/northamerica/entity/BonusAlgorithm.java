package com.human.northamerica.entity;

import java.util.HashMap;
import java.util.Map;

public  class BonusAlgorithm {

    public static Map<Integer, Bonus> bonusAlg=new HashMap<Integer, Bonus>();;

   static {
        bonusAlg.put(30, new Bonus(50.15, 53.56, 63.76, 67.11));
        bonusAlg.put(29, new Bonus(46.90, 49.78, 58.71, 61.50));
        bonusAlg.put(28, new Bonus(43.77, 46.15, 53.86, 56.13));
        bonusAlg.put(27, new Bonus(40.75, 42.66, 49.22, 51.00));
        bonusAlg.put(26, new Bonus(37.84, 39.32, 44.78, 46.11));
        bonusAlg.put(25, new Bonus(35.05, 36.12, 40.54, 41.45));
        bonusAlg.put(24, new Bonus(32.37, 33.06, 36.51, 37.04));
        bonusAlg.put(23, new Bonus(29.80, 30.15, 32.68, 32.87));
        bonusAlg.put(22, new Bonus(27.34, 27.39, 29.06, 28.94));
        bonusAlg.put(21, new Bonus(25.00, 24.76, 25.64, 25.24));
        bonusAlg.put(20, new Bonus(22.77, 22.29, 22.42, 21.79));
        bonusAlg.put(19, new Bonus(20.65, 19.95, 19.41, 18.58));
        bonusAlg.put(18, new Bonus(18.64, 17.76, 16.61, 15.61));
        bonusAlg.put(17, new Bonus(16.75, 15.72, 14.00, 12.87));
        bonusAlg.put(16, new Bonus(14.97, 13.82, 11.60, 10.38));
        bonusAlg.put(15, new Bonus(13.30, 12.06, 9.41, 8.13));
        bonusAlg.put(14, new Bonus(11.74, 10.45, 7.42, 6.12));
        bonusAlg.put(13, new Bonus(10.30, 8.99, 5.63, 4.34));
        bonusAlg.put(12, new Bonus(8.97, 7.66, 4.05, 2.81));
        bonusAlg.put(11, new Bonus(7.75, 6.49, 2.67, 1.52));
        bonusAlg.put(10, new Bonus(6.64, 5.45, 1.50, 0.47));
        bonusAlg.put(9, new Bonus(5.65, 4.56, 0.53, 0.00));
        bonusAlg.put(8, new Bonus(4.77, 3.82, 0.00, 0.00));
        bonusAlg.put(7, new Bonus(4.00, 3.22, 0.00, 0.00));
        bonusAlg.put(6, new Bonus(3.34, 2.76, 0.00, 0.00));
        bonusAlg.put(5, new Bonus(2.80, 2.45, 0.00, 0.00));
        bonusAlg.put(4, new Bonus(2.37, 2.28, 0.00, 0.00));
        bonusAlg.put(3, new Bonus(2.05, 2.26, 0.00, 0.00));
        bonusAlg.put(2, new Bonus(1.85, 2.38, 0.00, 0.00));
        bonusAlg.put(1, new Bonus(1.75, 2.65, 0.00, 0.00));
    }

    public static class Bonus {

        private Double read;
        private Double listen;
        private Double speak;
        private Double write;

        public Bonus(Double read, Double listen, Double speak, Double write) {
            super();
            this.read = read;
            this.listen = listen;
            this.speak = speak;
            this.write = write;
        }

        public Double getRead() {
            return read;
        }

        public void setRead(Double read) {
            this.read = read;
        }

        public Double getListen() {
            return listen;
        }

        public void setListen(Double listen) {
            this.listen = listen;
        }

        public Double getSpeak() {
            return speak;
        }

        public void setSpeak(Double speak) {
            this.speak = speak;
        }

        public Double getWrite() {
            return write;
        }

        public void setWrite(Double write) {
            this.write = write;
        }
    }

    public static Map<Integer, Bonus> getBonusAlg() {
        return bonusAlg;
    }

}
