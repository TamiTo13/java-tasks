package bg.sofia.uni.fmi.mjt.football;
import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.StringReader;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class FootballPlayerAnalyzerTest {

    @Test
    public void testAll() {
            String buff = "name;full_name;birth_date;age;height_cm;weight_kgs;positions;nationality;overall_rating;potential;value_euro;wage_euro;preferred_foot\n" +
                    "L. Messi;Lionel Andrés Messi Cuccittini;6/24/1987;31;170.18;72.1;CF,RW,ST;Argentina;94;94;110500000;565000;Left\n" +
                    "C. Eriksen;Christian  Dannemann Eriksen;2/14/1992;27;154.94;76.2;CAM,RM,CM;Denmark;88;89;69500000;205000;Right\n" +
                    "P. Pogba;Paul Pogba;3/15/1993;25;190.5;83.9;CM,CAM;France;88;91;73000000;255000;Right\n" +
                    "L. Insigne;Lorenzo Insigne;6/4/1991;27;162.56;59;LW,ST;Italy;88;88;62000000;165000;Right\n" +
                    "K. Koulibaly;Kalidou Koulibaly;6/20/1991;27;187.96;88.9;CB;Senegal;88;91;60000000;135000;Right\n" +
                    "V. van Dijk;Virgil van Dijk;7/8/1991;27;193.04;92.1;CB;Netherlands;88;90;59500000;215000;Right\n" +
                    "K. Mbappé;Kylian Mbappé;12/20/1998;20;152.4;73;RW,ST,RM;France;88;95;81000000;100000;Right\n" +
                    "S. Agüero;Sergio Leonel Agüero del Castillo;6/2/1988;30;172.72;69.9;ST;Argentina;89;89;64500000;300000;Right\n" +
                    "M. Neuer;Manuel Neuer;3/27/1986;32;193.04;92.1;GK;Germany;89;89;38000000;130000;Right\n" +
                    "E. Cavani;Edinson Roberto Cavani Gómez;2/14/1987;32;185.42;77.1;ST;Uruguay;89;89;60000000;200000;Right\n" +
                    "Sergio Busquets;Sergio Busquets i Burgos;7/16/1988;30;187.96;76.2;CDM,CM;Spain;89;89;51500000;315000;Right\n" +
                    "T. Courtois;Thibaut Courtois;5/11/1992;26;198.12;96.2;GK;Belgium;89;90;53500000;240000;Left\n" +
                    "M. ter Stegen;Marc-André ter Stegen;4/30/1992;26;187.96;84.8;GK;Germany;89;92;58000000;240000;Right\n" +
                    "A. Griezmann;Antoine Griezmann;3/21/1991;27;175.26;73;CF,ST;France;89;90;78000000;145000;Left\n" +
                    "M. Salah;Mohamed  Salah Ghaly;6/15/1992;26;175.26;71.2;RW,ST;Egypt;89;90;78500000;265000;Left\n" +
                    "P. Dybala;Paulo Bruno Exequiel Dybala;11/15/1993;25;152.4;74.8;CAM,RW;Argentina;89;94;89000000;205000;Left";
            Reader reader = new StringReader(buff);
            FootballPlayerAnalyzer a = new FootballPlayerAnalyzer(reader);
            a.getAllPlayers();
            a.getAllNationalities();

            Player player1 = Player.of("Messi;Lionel Andrés Messi Cuccittini;6/24/1987;31;170.18;72.1;CF,RW,ST;Argentina;94;94;110500000;565000;Left");
            a.getSimilarPlayers(player1);
            assertThrows(NoSuchElementException.class, () -> a.getHighestPaidPlayerByNationality("asdasd"));
            a.getPlayersByFullNameKeyword("Messi");
            a.getTopProspectPlayerForPositionInBudget(Position.ST,500000);
            a.getTopProspectPlayerForPositionInBudget(Position.CAM, 6000000);
            a.groupByPosition();


    }

}
