package CarFactory.Factory;

import ThreadPool.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Assembler implements Task {
    private final List<Stock> inStocks;
    private final Stock outStock;

    public Assembler(LinkedList<Stock> inStocks, Stock outStock) {
        this.inStocks = inStocks;
        this.outStock = outStock;
    }

    protected abstract Product assemble(ArrayList<Part> parts);

    protected abstract void incrementProductStockSize();

    @Override
    public void doWork() {
        ArrayList<Part> parts = new ArrayList<>();
        for (Stock stock: inStocks) {
            Product p = stock.get();
            if (p instanceof Part) {
                parts.add((Part)p);
            }
            else {
                System.err.println("Assembler: in-stock have instance not of Part");
                stock.add(p);
                return;
            }
        }
        Product result = assemble(parts);
        if (result == null) {
            System.err.println("Assembling error");
            for (int i = 0; i < inStocks.size(); ++i) {
                inStocks.get(i).add(parts.get(i));
            }
            return;
        }
        synchronized (outStock) {
            outStock.add(result);
            incrementProductStockSize();
        }
    }
}
