package collection;

/**
 * Класс - координаты объекта HumanBeing
 */
public class Coordinates {
    /**
     * Параметры x и y
     * x: Максимальное значение поля: 808
     * y: Максимальное значение поля: 428
     */
    private float x;
    private long y;

    /**
     * Конструктор класса координат
     * @param x - координата x
     * @param y - координата y
     */
    public Coordinates(float x, long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Метод возвращает координату X
     * @return координату X
     */
    public float getX() {
        return x;
    }

    /**
     * Метод устанавливает координату X
     * @param x - координата X
     */
    public void setX(float x) {
        this.x = x < 808 ? x : null;
    }

    /**
     * Метод возвращает координату Y
     * @return координату Y
     */
    public long getY() {
        return y;
    }

    /**
     * Метод устанавливает координату Y
     * @param y - координата Y
     */
    public void setY(long y) {
        this.y = y < 428 ? y : null;
    }

    /**
     * Метод приводящий параметры координат к строковому значению
     * @return
     */
    @Override
    public String toString() {
        try {
            return "координаты X и Y = " + getX() + ", " + getY();
        } catch (NullPointerException e) {
            return "координаты не введены, либо не подходят под условие (X больше 808, Y больше 428)";
        }
    }
}