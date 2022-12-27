package prikol;
//МОДЕЛЬ ТАБЛИЦЫ
import javax.swing.table.AbstractTableModel;

public class FunctionTableModel extends AbstractTableModel{

    private Double from, to, step;
    private  Double[] coefficients;

    public FunctionTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    @Override
    // Вычислить количество точек между началом и концом отрезка
    // исходя из шага табулирования
    public int getRowCount() {
        return (int)(Math.ceil((to - from) / step)) + 1;
    }

    @Override
    // В данной модели 3 столбца
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        Double x = from + step * rowIndex;
        Double y= toGorner(x);
        double Her = y;
        Boolean z;
        String s = String.valueOf((y));
        if(y- (int)Her==0){
            z = true;
        }
        else{  z =false;
        }
        switch (columnIndex){
            case 0: return x;
            case 1: return y;
            case 2: return z;
        }
        return null;
    }
    public Double toGorner(Double x){
        Double y = coefficients[coefficients.length - 1];
        for (int i = coefficients.length - 2; i > 0; i--){
            y+=y*x + coefficients[i]*x;
        }
        return y;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex == 2) return Boolean.class;
        return Double.class;
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            // Название 1-го столбца
            case 0: return "Значение Х";
            // Название 2-го столбца
            case 1: return "Значение многочлена";
            // Название 3-го столбца
            case 2: return " точное значение?";
        }
        return "";
    }
}
