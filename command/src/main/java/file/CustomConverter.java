package file;

import com.google.gson.*;
import collection.*;

import java.lang.reflect.Type;
import java.util.Date;

public class CustomConverter implements JsonSerializer<HumanBeing>, JsonDeserializer<HumanBeing> {
    Gson gson = new Gson();
    CollectionManager collectionManager = new CollectionManager();

    @Override
    public HumanBeing deserialize(
            JsonElement json,
            Type type,
            JsonDeserializationContext context
    ) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        int id = collectionManager.generateNewId();
        String name = jsonObject.get("name").getAsString();
        JsonObject coordinates = jsonObject.get("coordinates").getAsJsonObject();
        float x = coordinates.get("x").getAsFloat();
        int y = coordinates.get("y").getAsInt();
        Coordinates coords = new Coordinates(x, y);
        Date creationDate = new Date();
        boolean realHero = jsonObject.get("realHero").getAsBoolean();
        Boolean hasToothpick = jsonObject.get("hasToothpick").isJsonNull() ? null : jsonObject.get("hasToothpick").getAsBoolean();
        int impactSpeed = jsonObject.get("impactSpeed").getAsInt();
        String soundtrackName = jsonObject.get("soundtrackName").getAsString();
        Float minutesOfWaiting = jsonObject.get("minutesOfWaiting").isJsonNull() ? null : jsonObject.get("minutesOfWaiting").getAsFloat();
        WeaponType weaponType = WeaponType.valueOf(jsonObject.get("weaponType").getAsString());
        JsonObject carObject = jsonObject.get("car").getAsJsonObject();
        boolean cool = carObject.get("cool").getAsBoolean();
        Car car = new Car(cool);

        return new HumanBeing(
                id,
                name,
                coords,
                creationDate,
                realHero,
                hasToothpick,
                impactSpeed,
                soundtrackName,
                minutesOfWaiting,
                weaponType,
                car
        );
    }

    @Override
    public JsonElement serialize(
            HumanBeing humanBeing,
            Type type,
            JsonSerializationContext jsonSerializationContext
    ) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", humanBeing.getId());
        jsonObject.addProperty("name", humanBeing.getName());
        jsonObject.add("coordinates", gson.toJsonTree(humanBeing.getCoordinates()));
        jsonObject.addProperty("realHero", humanBeing.getRealHero());
        jsonObject.addProperty("hasToothpick", humanBeing.getHasToothpick());
        jsonObject.addProperty("impactSpeed", humanBeing.getImpactSpeed());
        jsonObject.addProperty("soundtrackName", humanBeing.getSoundtrackName());
        jsonObject.addProperty("minutesOfWaiting", humanBeing.getMinutesOfWaiting());
        jsonObject.add("weaponType", gson.toJsonTree(humanBeing.getWeaponType()));
        jsonObject.add("car", gson.toJsonTree(humanBeing.getCar()));

        return jsonObject;
    }
}