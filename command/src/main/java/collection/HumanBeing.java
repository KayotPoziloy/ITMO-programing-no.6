package collection;

import java.io.Serializable;
import java.util.Date;

/**
 * Класс, представляющий сущность человека
 */
public class HumanBeing implements Serializable {
    /**
     * Уникальный id объекта.
     * Значение поля должно быть больше 0,
     * Значение этого поля должно быть уникальным,
     * Значение этого поля должно генерироваться автоматически
     */
    private Integer id;
    /**
     * Имя объекта.
     * Поле не может быть null,
     * Строка не может быть пустой
     */
    private String name;
    /**
     * Координаты объекта.
     * Поле не может быть null
     */
    private Coordinates coordinates;
    /**
     * Время создания объекта.
     * Поле не может быть null,
     * Значение этого поля должно генерироваться автоматически
     */
    private Date creationDate;
    /**
     * Герой ли объект.
     * Поле не может быть null
     */
    private Boolean realHero;
    /**
     * Есть ли зубочистка у объекта.
     * Поле может быть null
     */
    private Boolean hasToothpick;
    /**
     * Скорость удара объекта.
     */
    private int impactSpeed;
    /**
     * Название саундтрека объекта.
     * Поле не может быть null
     */
    private String soundtrackName;
    /**
     * Время ожидания в минутах.
     * Поле может быть null
     */
    private Float minutesOfWaiting;
    /**
     * Тип оружия у объекта.
     * Поле не может быть null
     */
    private WeaponType weaponType;
    /**
     * Крутость автомобиля у объекта.
     * Поле не может быть null
     */
    private Car car;

    /**
     * Конструктор объекта класса
     * @param name             имя объекта
     * @param coordinates      координаты объекта
     * @param creationDate     дата создания объекта
     * @param realHero         герой ли объект
     * @param hasToothpick     наличие зубочистки у объекта
     * @param impactSpeed      скорость удара объекта
     * @param soundtrackName   саундтрек объекта
     * @param minutesOfWaiting время ожидания объекта
     * @param weaponType       тип оружия у объекта
     * @param car              состояние машины объекта
     */
    public HumanBeing(
            Integer id,
            String name,
            Coordinates coordinates,
            Date creationDate,
            Boolean realHero,
            Boolean hasToothpick,
            int impactSpeed,
            String soundtrackName,
            Float minutesOfWaiting,
            WeaponType weaponType,
            Car car
    ) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.soundtrackName = soundtrackName;
        this.minutesOfWaiting = minutesOfWaiting;
        this.weaponType = weaponType;
        this.car = car;
    }

    /**
     * Метод вызывает id объекта
     * @return id объекта
     */
    public Integer getId() {
        return id;
    }

    /**
     * Метод устанавливает значение id для объекта
     * @param id - id объекта
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Метод вызывает имя класса
     * @return name объекта
     */
    public String getName() {
        return name;
    }

    /**
     * Метод устанавливает имя объекта
     * @param name объекта
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод вызывает координаты
     * @return coordinates объекта
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Метод устанавливает координату x
     * @param x объекта
     */
    public void setCoordinateX(float x) {
        this.coordinates.setX(x);
    }

    /**
     * Метод устанавливает координату y
     * @param y объекта
     */
    public void setCoordinateY(long y) {
        this.coordinates.setY(y);
    }

    /**
     * Метод вызывает время создания объекта
     * @return время создания объекта
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Метод устанавливает время создания объекта
     * @param creationDate - время создания объекта
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    /**
     * Метод возвращает герой ли объект
     * @return герой или нет
     */
    public Boolean getRealHero() {
        return realHero;
    }

    /**
     * Метод устанавливает герой ли объект
     * @param realHero - герой или нет
     */
    public void setRealHero(Boolean realHero) {
        this.realHero = realHero;
    }

    /**
     * Метод возвращает, есть ли зубочистка у объекта
     * @return наличие зубочистки
     */
    public Boolean getHasToothpick() {
        return hasToothpick;
    }

    /**
     * Метод устанавливает наличие зубочистки у объекта
     * @param hasToothpick - наличие зубочистки у объекта
     */
    public void setHasToothpick(Boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    /**
     * Возвращает скорость удара объекта
     * @return скорость удара оъекта
     */
    public int getImpactSpeed() {
        return impactSpeed;
    }

    /**
     * Метод устанавливает скорость удара объекта
     * @param impactSpeed - скорость удара объекта
     */
    public void setImpactSpeed(int impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    /**
     * Метод возвращает имя саундтрека объекта
     * @return имя саундтрека объекта
     */
    public String getSoundtrackName() {
        return soundtrackName;
    }

    /**
     * Метод устанавливает имя саундтрека объекта
     * @param soundtrackName - имя саундтрека объекта
     */
    public void setSoundtrackName(String soundtrackName) {
        this.soundtrackName = soundtrackName;
    }

    /**
     * Метод возвращает время ожидания объекта
     * @return время ожидания объекта
     */
    public Float getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    /**
     * Метод устанавливает время ожидания объекта
     * @param minutesOfWaiting - время ожидания объекта
     */
    public void setMinutesOfWaiting(Float minutesOfWaiting) {
        this.minutesOfWaiting = minutesOfWaiting;
    }

    /**
     * Метод возвращает тип оружия объекта
     * @return тип оружия объекта
     */
    public WeaponType getWeaponType() {
        return weaponType;
    }

    /**
     * Метод устанавливает тип оружия объекта
     * @param weaponType - тип оружия объекта
     */
    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    /**
     * Метод возвращает крутость автомобиля объекта
     * @return крутость автомобиля объекта
     */
    public Car getCar() {
        return car;
    }

    /**
     * Метод устанавливает крутость автомобиля объекта
     * @param car - крутость автомобиля объекта
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * Метод, выводящий все значения объекта
     * @return значения объекта
     */
    @Override
    public String toString() {
        return "\n=============\n" +
                "id:\n " + id +
                ",\nname: \n " + name  +
                ",\ncoordinates: \n " + coordinates +
                ",\ncreationDate: \n " + creationDate +
                ",\nrealHero: \n " + realHero +
                ",\nhasToothpick: \n " + hasToothpick +
                ",\nimpactSpeed: \n " + impactSpeed +
                ",\nsoundtrackName: \n '" + soundtrackName + '\'' +
                ",\nminutesOfWaiting: \n " + minutesOfWaiting +
                ",\nweaponType: \n " + weaponType +
                ",\ncar: \n " + car;
    }
}