package file;

import collection.*;
import io.User;

import java.time.DateTimeException;
import java.time.Instant;
import java.util.Date;

/**
 * Класс, читающий данные для объекта HumanBeing
 */
public class HumanBeingReader {
    /**
     * Ссылка на объект типа User
     */
    private User user;
    /**
     * Менеджер коллекции
     */
    CollectionManager collectionManager = new CollectionManager();

    /**
     * Конструктор класса
     * @param user присваивает переданное значение полю user
     */
    public HumanBeingReader(User user) {
        this.user = user;
    }

    /**
     * Метод, читающий данные из консоли.
     * Ввод полей происходит в строго определенном порядке
     * @return возвращает объект типа HumanBeing
     */
    public HumanBeing read() {
        Instant time = Instant.now();
        return new HumanBeing(
                setId(),
                readName(),
                readCoordinates(),
                Date.from(time),
                readRealHero(),
                readHasToothpick(),
                readImpactSpeed(),
                readSoundtrackName(),
                readMinutesOfWaing(),
                readWeaponType(),
                readCar()
        );
    }

    public int setId() {
        return collectionManager.generateNewId();
    }

    /**
     * Метод читает name типа String из потока, указанного в User
     * При неправильном вводе запрашивает ввод поля снова
     * @return значение name
     */
    public String readName() {
        String name;

        while (true) {
            user.printCommand("имя (не null):");
            name = user.readLine().trim();
            if (name.equals("")) {
                user.printError("Значение поля не может быть пустым или null");
            } else {
                return name;
            }
        }
    }

    /**
     * Метод, читающий координату X типа float объекта HumanBeing
     * @return значение X
     */
    public float readCoordinateX() {
        float x;
        while (true) {
            try {
                user.printCommand("координата X (с плавающей запятой, не null, меньше 809):");
                x = Float.parseFloat(user.readLine().trim());
                if (x > 808) {
                    throw new IllegalArgumentException();
                } else {
                    return x;
                }
            } catch (NumberFormatException e) {
                System.err.println("Число должно быть типа float и не null");
            } catch (IllegalArgumentException  e) {
                System.out.println("Координата x должна быть меньше 809");
            }
        }
    }

    /**
     * Метод, читающий координату Y типа long объекта HumanBeing
     * @return значение Y
     */
    public long readCoordinateY() {
        long y;
        while (true) {
            try {
                user.printCommand("координата Y (целое, не null, меньше 429):");
                y = Long.parseLong(user.readLine().trim());
                if (y > 808) {
                    throw new IllegalArgumentException();
                } else {
                    return y;
                }
            } catch (NumberFormatException e) {
                System.err.println("Число должно быть типа long и не null");
            } catch (IllegalArgumentException  e) {
                System.out.println("Координата y должна быть меньше 489");
            }
        }
    }

    /**
     * Метод, читающий координаты X и Y
     * @return значение Coordinates
     */
    public Coordinates readCoordinates() {
        return new Coordinates(readCoordinateX(), readCoordinateY());
    }

    /**
     * Метод определяющий realHero
     * @return значение realHero
     */
    public Boolean readRealHero() {
        boolean realHero;
        while (true) {
            try {
                user.printCommand("герой? (да или нет):");
                String string = user.readLine().trim();
                if (string.equals("")) {
                    System.err.println("ответ не может быть null");
                } else {
                    if (string.trim().equals("да")) {
                        realHero = true;
                    } else if (string.trim().equals("нет")) {
                        realHero = false;
                    } else {
                        throw new IllegalArgumentException();
                    }
                    return realHero;
                }
            } catch (NumberFormatException e) {
                System.err.println("ответ не может быть null");
            } catch (IllegalArgumentException e) {
                System.out.println("ответ может быть только да или нет");
            }
        }
    }

    /**
     * Метод, определяющий hasToothpick
     * @return значение hasToothpick
     */
    public Boolean readHasToothpick() {
        boolean hasToothpick;
        while (true) {
            try {
                user.printCommand("есть зубочистка? (да или нет):");
                String string = user.readLine().trim();
                if (string.equals("")) {
                    System.err.println("ответ не может быть null");
                } else {
                    if (string.trim().equals("да")) {
                        hasToothpick = true;
                    } else if (string.trim().equals("нет")) {
                        hasToothpick = false;
                    } else {
                        throw new IllegalArgumentException();
                    }
                    return hasToothpick;
                }
            } catch (NumberFormatException e) {
                System.err.println("ответ не может быть null");
            } catch (IllegalArgumentException e) {
                System.out.println("ответ может быть только да или нет");
            }
        }
    }

    public Date readCreationDate() {
        while (true) {
            try {
                return new Date();
            } catch (DateTimeException e) {
                user.printError("временная проблема (проблема со временем)");
            }
        }
    }

    /**
     * Метод, читающий значение ImpactSpeed
     * @return скорость удара объекта HumanBeing
     */
    public int readImpactSpeed() {
        int impactSpeed;
        while (true) {
            try {
                user.printCommand("скорость удара (целое число):");
                String string = user.readLine().trim();
                impactSpeed = Integer.parseInt(string);
                return impactSpeed;
            } catch (NumberFormatException e) {
                System.err.println("число должно быть типа int");
            }
        }
    }

    /**
     * Метод, читающий значение SoundtrackName
     * @return название саундтрека
     */
    public String readSoundtrackName() {
        String soundtrackName;
        while (true) {
            try {
                user.printCommand("название саундтрека (не null): ");
                soundtrackName = user.readLine().trim();
                if (soundtrackName.equals("")) {
                    throw new IllegalArgumentException();
                } else {
                    return soundtrackName;
                }
            } catch (IllegalArgumentException e) {
                user.printError("Значение поля не может быть пустым или null");
            }
        }
    }

    public Float readMinutesOfWaing() {
        String string;
        Float minutes;
        while (true) {
            try {
                user.printCommand("введите количество минут ожидания ответа HumanBeing от компьютера: ");
                string = user.readLine().trim();
                minutes = Float.parseFloat(string);
                if (string.equals("")) {
                    throw new IllegalArgumentException();
                } else {
                    return minutes;
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Значение поля не может быть пустым или null");
            }
        }
    }

    /**
     * Метод считывает тип оружия
     * @return тип оружия
     */
    public WeaponType readWeaponType() {
        String string;
        WeaponType weaponType = null;
        while (true) {
            try {
                user.printCommand("выбери оружие из списка: \n " +
                        "кувалда, шотган, винтовка, миниган: ");
                string = user.readLine().trim();
                if (string.equals("")) {
                    throw new IllegalArgumentException();
                } else {
                    if (string.equals("кувалда"))
                        weaponType = WeaponType.HAMMER;
                    else if (string.equals("шотган"))
                        weaponType = WeaponType.SHOTGUN;
                    else if (string.equals("винтовка"))
                        weaponType = WeaponType.RIFLE;
                    else if (string.equals("миниган"))
                        weaponType = WeaponType.MACHINE_GUN;
                    return weaponType;
                }
            } catch (IllegalArgumentException e) {
                System.err.println("оружие не может быть пустым и null");
            }
        }
    }

    /**
     * Метод спрашивает, крутая ли машина и получает ответ
     * @return крутая машина или нет
     */
    public boolean readCarCool() {
        String string;
        boolean carCool;
        while (true) {
            try {
                user.printCommand("машина крутая? (да или нет): ");
                string = user.readLine().trim();
                if (string.equals("да")) {
                    carCool = true;
                } else if (string.equals("нет")){
                    carCool = false;
                } else {
                    throw new IllegalArgumentException();
                }
                return carCool;
            } catch (IllegalArgumentException e) {
                System.err.println("значение не может быть null или пустым");
            }
        }
    }

    /**
     * Метод для чтения крутости машины
     * @return да или нет в зависимости от readCarCool
     */
    public Car readCar() {
        return new Car(readCarCool());
    }
}