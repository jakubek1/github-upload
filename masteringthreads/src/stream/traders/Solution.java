package stream.traders;

import java.util.*;
import java.util.stream.*;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

public class Solution {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Trader> traders = Arrays.asList(raoul, mario, alan, brian);

        List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

        printResultAndSeparator(//1
        transactions.stream().filter(t -> t.getYear() == 2011).sorted(comparing(t -> t.getValue())).collect(toList()));

        printResultAndSeparator(//2
        transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(toList()));

        printResultAndSeparator(//3
            transactions.stream().map(t -> t.getTrader()).filter(trader -> trader.getCity().equals("Cambridge")).distinct().sorted(comparing(Trader::getName)).collect(toList()));

        printResultAndSeparator(//4
            Collections.singletonList(transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().reduce("", (a, b) -> a + b)));

        printResultAndSeparator(//5
            Collections.singletonList(transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"))));

        printResultAndSeparator(//6
            transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge")).map(t -> t.getValue()).collect(toList()));

        printResultAndSeparator(//7
            Collections.singletonList(transactions.stream().map(t -> t.getValue()).reduce(Integer::max)));

        printResultAndSeparator(//8
            Collections.singletonList(transactions.stream().min(comparing(Transaction::getValue))));


    }

    public static void printResultAndSeparator(List<? extends Object> result) {
        result.forEach(System.out::print);
        System.out.println("\n---------------------------------------------------------");
    }
}
