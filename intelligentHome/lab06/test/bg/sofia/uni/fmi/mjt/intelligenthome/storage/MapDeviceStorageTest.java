package bg.sofia.uni.fmi.mjt.intelligenthome.storage;

import bg.sofia.uni.fmi.mjt.intelligenthome.device.IoTDevice;
import bg.sofia.uni.fmi.mjt.intelligenthome.device.RgbBulb;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class MapDeviceStorageTest {


    @Test
    void testMapDeviceStorage() {
        MapDeviceStorage a = new MapDeviceStorage();
        String test = "test";
        Double price = 1.5;
        IoTDevice dummy = new RgbBulb(test, price, LocalDateTime.now());
        a.store(test,dummy);
        a.get(test);
        a.exists(test);
        a.listAll();
        a.delete(test);
    }
}
