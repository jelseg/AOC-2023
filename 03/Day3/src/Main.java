import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        MachineSchematics machineSchematics = new MachineSchematics("input/opgave.txt");

        machineSchematics.checkPartNumbers();

        System.out.println(machineSchematics.getSumPartNumbers());
        System.out.println(machineSchematics.getSumGearNumbers());
    }
}