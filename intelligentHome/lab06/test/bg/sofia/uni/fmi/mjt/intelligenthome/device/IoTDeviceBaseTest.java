package bg.sofia.uni.fmi.mjt.intelligenthome.device;

import bg.sofia.uni.fmi.mjt.intelligenthome.device.AmazonAlexa;
import bg.sofia.uni.fmi.mjt.intelligenthome.device.IoTDeviceBase;
import bg.sofia.uni.fmi.mjt.intelligenthome.device.RgbBulb;
import bg.sofia.uni.fmi.mjt.intelligenthome.device.WiFiThermostat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;

public class IoTDeviceBaseTest {

    @Test
    void testConstructors() {
        IoTDeviceBase rgbBulb = new RgbBulb("bulb", 1.5, LocalDateTime.now());
        IoTDeviceBase WifiThermostat = new WiFiThermostat("wifi", 2.5, LocalDateTime.now());
        IoTDeviceBase AmazonAlexa = new AmazonAlexa("alexa", 3.5, LocalDateTime.now());


        Assertions.assertEquals("BLB-bulb-0",rgbBulb.getId());
        Assertions.assertEquals("TMST-wifi-1",WifiThermostat.getId());
        Assertions.assertEquals("SPKR-alexa-2",AmazonAlexa.getId());

        rgbBulb.getId();
        rgbBulb.getName();
        rgbBulb.getInstallationDateTime();
        rgbBulb.getType();
        rgbBulb.getPowerConsumptionKWh();
        WifiThermostat.setRegistration(LocalDateTime.now());
        WifiThermostat.getRegistration();

    }

}
