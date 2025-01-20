package bg.sofia.uni.fmi.mjt.gym;

import bg.sofia.uni.fmi.mjt.gym.member.Address;
import bg.sofia.uni.fmi.mjt.gym.member.GymMember;

import java.util.Comparator;

public class ProximityComparator implements Comparator<GymMember> {

    private Address address;

    public ProximityComparator(Address address) {
        this.address = address;
    }

    @Override
    public int compare(GymMember o1, GymMember o2) {
        return (int)Math.ceil(o1.getAddress().getDistanceTo(address) - o2.getAddress().getDistanceTo(address));
    }
}
