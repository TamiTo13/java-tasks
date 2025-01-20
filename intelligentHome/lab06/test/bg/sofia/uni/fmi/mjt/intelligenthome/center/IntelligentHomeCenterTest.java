package bg.sofia.uni.fmi.mjt.intelligenthome.center;

import bg.sofia.uni.fmi.mjt.intelligenthome.center.exceptions.DeviceAlreadyRegisteredException;
import bg.sofia.uni.fmi.mjt.intelligenthome.center.exceptions.DeviceNotFoundException;
import bg.sofia.uni.fmi.mjt.intelligenthome.device.IoTDevice;
import bg.sofia.uni.fmi.mjt.intelligenthome.device.RgbBulb;
import bg.sofia.uni.fmi.mjt.intelligenthome.storage.MapDeviceStorage;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntelligentHomeCenterTest {

    private MapDeviceStorage storage = new MapDeviceStorage();

    private IntelligentHomeCenter center;

    @Test
    void testRegister() {
        String test = "test1";
        Double test1 = 1.5;
        IoTDevice dummy = new RgbBulb(test, test1, LocalDateTime.now());
        center = new IntelligentHomeCenter(storage);
        try {
            center.register(dummy);
        } catch (Throwable e) {

        }
        assertThrows(IllegalArgumentException.class, ()->center.register(null));
        assertThrows(DeviceAlreadyRegisteredException.class, () -> center.register(dummy));
    }

    @Test
    void testUnregister() {
        String test = "test1";
        Double test1 = 1.5;
        IoTDevice dummy = new RgbBulb(test, test1, LocalDateTime.now());
        center = new IntelligentHomeCenter(storage);

        assertThrows(IllegalArgumentException.class, ()->center.unregister(null));
        assertThrows(DeviceNotFoundException.class, () -> center.unregister(dummy));

    }

    @Test
    void testGetDeviceById() {
        String test = "";
        String not = "non-existent";
        Double test1 = 1.5;
        //IoTDevice dummy = new RgbBulb(test, test1, LocalDateTime.now());
        center = new IntelligentHomeCenter(storage);

        assertThrows(IllegalArgumentException.class, ()->center.getDeviceById(null));
        assertThrows(IllegalArgumentException.class, ()->center.getDeviceById(test));
        assertThrows(DeviceNotFoundException.class, () -> center.getDeviceById(not));
    }

    @Test
    void testDeviceQuantityPerType() {
        String test1 = "1";
        String test2 = "2";
        String test3 = "3";
        String not = "non-existent";
        Double price = 1.5;
        IoTDevice dummy = new RgbBulb(test1, price, LocalDateTime.now());
        IoTDevice dummy2 = new RgbBulb(test2, price, LocalDateTime.now());
        IoTDevice dummy3 = new RgbBulb(test3, price, LocalDateTime.now());
        int expected = 3;
        center = new IntelligentHomeCenter(storage);
        try {
            center.register(dummy);
            center.register(dummy2);
            center.register(dummy3);
        } catch (Throwable e) {
            //
        }
        assertThrows(IllegalArgumentException.class, ()->center.getDeviceQuantityPerType(null));
        assertEquals(expected, center.getDeviceQuantityPerType(dummy.getType()));
    }

    @Test
    void testGetTopNDevicesByPowerConsumption() {
        String test1 = "1";
        String test2 = "2";
        String test3 = "3";
        //String not = "non-existent";
        Double price1 = 1.5;
        Double price2 = 2.5;
        Double price3  = 3.5;
        IoTDevice dummy1 = new RgbBulb(test1, price1, LocalDateTime.now().minusDays(1));
        IoTDevice dummy2 = new RgbBulb(test2, price2, LocalDateTime.now().minusDays(1));
        IoTDevice dummy3 = new RgbBulb(test3, price3, LocalDateTime.now().minusDays(1));
        center = new IntelligentHomeCenter(storage);
        try {
            center.register(dummy1);
            center.register(dummy2);
            center.register(dummy3);
        } catch (Throwable e) {
            //
        }
        ArrayList<String> arr = (ArrayList<String>) center.getTopNDevicesByPowerConsumption(4);
        assertThrows(IllegalArgumentException.class, ()->center.getTopNDevicesByPowerConsumption(-1));
        assertEquals(3,arr.size());
        assertEquals(dummy3.getId(), arr.getFirst());
    }

    @Test
    void testGetFirstNDevicesByRegistration() {
        String test1 = "1";
        String test2 = "2";
        String test3 = "3";
        //String not = "non-existent";
        Double price1 = 1.5;
        Double price2 = 2.5;
        Double price3  = 3.5;
        int one = 1;

        IoTDevice dummy1 = new RgbBulb(test1, price1, LocalDateTime.now().minusDays(one));
        dummy1.setRegistration(LocalDateTime.now().minusDays(one++));
        IoTDevice dummy2 = new RgbBulb(test2, price2, LocalDateTime.now().minusDays(one));
        dummy2.setRegistration(LocalDateTime.now().minusDays(one++));
        IoTDevice dummy3 = new RgbBulb(test3, price3, LocalDateTime.now().minusDays(one));
        dummy3.setRegistration(LocalDateTime.now().minusDays(one));

        center = new IntelligentHomeCenter(storage);
        try {
            center.register(dummy1);
            center.register(dummy2);
            center.register(dummy3);
        } catch (Throwable e) {
            //
        }
        ArrayList<IoTDevice> arr = (ArrayList<IoTDevice>) center.getFirstNDevicesByRegistration(4);
        assertThrows(IllegalArgumentException.class, ()->center.getTopNDevicesByPowerConsumption(-1));
        assertEquals(3,arr.size());
        //assertEquals(dummy3, arr.getFirst());
    }


}
