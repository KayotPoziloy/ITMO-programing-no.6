package collection;

import file.FileManager;

import java.util.*;

/**
 * Менеджер коллекции объектов тип HumanBeing
 */
public class CollectionManager {
    /**
     * Коллекция HumanBeing
     */
    ArrayDeque<HumanBeing> humanBeingArrayDeque;
    /**
     * Время инициализации коллекции
     */
    Date initializationDate = new Date();
    /**
     * Конструктор, который создает объект менеджера
     */
    public CollectionManager() {
        humanBeingArrayDeque = new ArrayDeque<>();
    }

    /**
     * Метод, который возвращает информацию о коллекции
     */
    public String info() {
        return "Тип коллекции: " + humanBeingArrayDeque.getClass().getName()
        + "\nТип элементов коллекции: " + HumanBeing.class.getName()
        + "\nДата инициализации коллекции: " + initializationDate
        + "\nКоличество элементов в коллекции: " + humanBeingArrayDeque.size();
    }

    /**
     * Метод, который выводит информацию об элементах коллекции
     */
    public List<HumanBeing> show() {
        List<HumanBeing> humanBeings = new ArrayList<>();
        if (humanBeingArrayDeque.isEmpty()) {
            System.out.println("В коллекции нет элементов");
            return null;
        } else {
            for (HumanBeing human : humanBeingArrayDeque) {
                humanBeings.add(human);
            }
        }
        return humanBeings;
    }

    /**
     * Метод, удаляющий все элементы коллекции
     */
    public void clear() {
        humanBeingArrayDeque.clear();
    }

    /**
     * Метод, который добавляет объект класса HumanBeing в коллекцию
     * @param humanBeing элемент, который добавляется в коллекцию
     */
    public void add(HumanBeing humanBeing) {
        humanBeingArrayDeque.add(humanBeing);
    }

    /**
     * счетчик id
     */
    private static int idCounter = 0;

    /**
     * Метод генерит уникальный id
     * @return уникальный id и прибавляет счетчик
     */
    public static synchronized int generateNewId() {
        return ++idCounter;
    }

    /**
     * Метод, удаляющий все объекты, id которого меньше заданого
     * @param id - id, меньше которого все удаляются
     */
    public void removeLower(Integer id) {
        Iterator<HumanBeing> iterator = humanBeingArrayDeque.iterator();
        boolean removed = true;
        while (iterator.hasNext()) {
            Integer next = iterator.next().getId();
            if (next < id) {
                iterator.remove();
                System.out.println("элементы удалены из коллекции");
            }
        }
    }

    /**
     * Метод, удаляющий объект, id которого равен заданому
     * @param id - элемент с этим id удаляется
     */
    public void removeById(Integer id) {
        Iterator<HumanBeing> iterator = humanBeingArrayDeque.iterator();
        boolean removed = true;
        while (iterator.hasNext()) {
            Integer next = iterator.next().getId();
            if (next.equals(id)) {
                iterator.remove();
                System.out.println("элементы удалены из коллекции");
                break;
            } else {
                removed = false;
            }
        }
        if (!removed) {
            System.out.println("элемент с id " + id + " не найден");
        }
    }

    /**
     * Метод получает нужный объект HumanBeing по id
     * @param id - id для сравнения и поиска подходящего объекта
     * @return нужно объект
     */
    public HumanBeing getByID(Integer id) {
        for (HumanBeing human : humanBeingArrayDeque) {
            if (human.getId().equals(id)) {
                return human;
            }
        }
        return null;
    }

    /**
     * Метод получается количество минут ожидания
     * @return сумма ожидания в минутах
     */
    public Float getSumOfMinutesOfWaiting() {
        float sum = 0f;
        for (HumanBeing human : humanBeingArrayDeque) {
            if (human.getMinutesOfWaiting() != null)
                sum += human.getMinutesOfWaiting();
        }
        return sum;
    }

    /**
     * Метод находит количество саундтреков с определенным названием
     * @param soundtrackName - название саундтрека для поиска совпадений
     * @return количество саундтреков с таким названием в коллекции
     */
    public int countBySoundtrackName(String soundtrackName) {
        int count = 0;
        for (HumanBeing human : humanBeingArrayDeque) {
            if (human.getSoundtrackName() != null && human.getSoundtrackName().equals(soundtrackName)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Метод находит объект с наименьшим ImpactSpeed в коллекции
     * @return объект HumanBeing с наименьшим ImpactSpeed
     */
    public HumanBeing getMinByImpactSpeed() {
        HumanBeing minHuman = null;
        float minImpactSpeed = Float.MAX_VALUE;
        for (HumanBeing human : humanBeingArrayDeque) {
            if (human.getImpactSpeed() < minImpactSpeed) {
                minHuman = human;
                minImpactSpeed = human.getImpactSpeed();
            }
        }
        return minHuman;
    }

    /**
     * Метод находит объект с наибольшим ImpactSpeed в коллекции
     * @return объект HumanBeing с наибольшим ImpactSpeed
     */
    public HumanBeing getMaxByImpactSpeed() {
        HumanBeing maxHuman = null;
        float maxImpactSpeed = Float.MIN_VALUE;
        for (HumanBeing human : humanBeingArrayDeque) {
            if (human.getImpactSpeed() > maxImpactSpeed) {
                maxHuman = human;
                maxImpactSpeed = human.getImpactSpeed();
            }
        }
        return maxHuman;
    }

    /**
     * Метод, который печатает уникальные значения параметра MinutesOfWaiting
     */
    public void printUniqueMinutesOfWaiting() {
        Set<Float> uniqueMinutesOfWaiting = new HashSet<>();
        for (HumanBeing human : humanBeingArrayDeque) {
            uniqueMinutesOfWaiting.add(human.getMinutesOfWaiting());
        }
        System.out.println("Уникальные значения поля MinutesOfWaiting: " + uniqueMinutesOfWaiting);
    }

    /**
     * Метод, который сохраняет коллекцию в файл
     */
    public void saveCollection() {
        FileManager fileManager = new FileManager();
        fileManager.writeCollection(humanBeingArrayDeque);
    }
}