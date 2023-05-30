package collection;

/**
 * Класс автомобиля объекта HumanBeing
 */
public class Car {
    /**
     * Крутость автомобиль
     */
    private boolean cool;

    /**
     * Конструктор класса
     * @param cool - крутость
     */
    public Car(boolean cool) {
        this.cool = cool;
    }

    /**
     * Возвращает крутая ли автомобиль
     * @return крутая ли автомобиль
     */
    public boolean isCool() {
        return cool;
    }

    /**
     * Устанавливает, крутой ли автомобиль
     * @param cool - крутой ли автомобиль
     */
    public void setCool(boolean cool) {
        this.cool = cool;
    }


    /**
     * Метод, возвращает значение крутости
     * @return крутая ли тачка
     * @throws NullPointerException возвращает "не было передано значение"
     */
    @Override
    public String toString() {
        try {
            return "тачка - " + (isCool() ? "крутая" : "отстой");
        } catch (NullPointerException e) {
            return "не было передано значение";
        }
    }
}