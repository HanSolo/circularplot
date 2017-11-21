/*
 * Copyright (c) 2017 by Gerrit Grunwald
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.hansolo.fx.circularplot;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;


public class DemoElection extends Application {
    private CircularPlot circluarPlot;

    @Override public void init() {
        // Setup Data
        // Eligible voters in Germany 2017: 61_500_000
        // Parties with their end results after the parliamentary election 2017 in Germany
        PlotItem union        = new PlotItem("Union (25.1%)", 15_436_500, Color.BLACK);
        PlotItem nichtWaehler = new PlotItem("Nichtwaehler (23.8%)", 14_637_000, Color.DARKGRAY);
        PlotItem spd          = new PlotItem("SPD (15.6%)", 9_594_000, Color.RED);
        PlotItem afd          = new PlotItem("AfD (9.6%)", 5_904_000, Color.BLUE);
        PlotItem fdp          = new PlotItem("FDP (8.2%)", 5_043_000, Color.YELLOW);
        PlotItem linke        = new PlotItem("Linke (7.0%)", 4_305_000, Color.PURPLE);
        PlotItem gruene       = new PlotItem("Gruene (6.8%)", 4_182_000, Color.LIME);
        PlotItem andere       = new PlotItem("Andere (3.8%)", 2_337_000, Color.LIGHTGRAY);


        // Migration of voters to AFD party
        nichtWaehler.addToOutgoing(afd, 1_200_000);
        union.addToOutgoing(afd, 980_000); // 5% moved from union to fdp
        andere.addToOutgoing(afd, 690_000);
        spd.addToOutgoing(afd, 470_000);
        linke.addToOutgoing(afd, 400_000);
        fdp.addToOutgoing(afd, 40_000);
        gruene.addToOutgoing(afd, 40_000);

        // Migration of voters to FDP party
        union.addToOutgoing(fdp, 1_360_000);
        nichtWaehler.addToOutgoing(fdp, 700_000);
        spd.addToOutgoing(fdp, 450_000);
        andere.addToOutgoing(fdp, 140_000);
        gruene.addToOutgoing(fdp, 110_000);
        linke.addToOutgoing(fdp, 60_000);

        // Migration of voters to UNION party
        nichtWaehler.addToOutgoing(union, 380_000);
        spd.addToOutgoing(union, 20_000);

        // Migration of voters to SPD party
        union.addToOutgoing(spd, 20_000);

        // Migration of voters to GRUENE party
        spd.addToOutgoing(gruene, 400_000);
        union.addToOutgoing(gruene, 50_000);

        // Migration of voters to LINKE party
        spd.addToOutgoing(linke, 430_000);
        nichtWaehler.addToOutgoing(linke, 270_000);
        gruene.addToOutgoing(linke, 170_000);
        union.addToOutgoing(linke, 90_000);


        // Setup Chart
        circluarPlot = CircularPlotBuilder.create()
                                          .prefSize(500, 500)
                                          .items(union, nichtWaehler, spd, afd, fdp, linke, gruene, andere)
                                          .showFlowDirection(true)
                                          .connectionOpacity(0.75)
                                          .minorTickMarksVisible(false)
                                          .build();
    }

    @Override public void start(Stage stage) {
        StackPane pane = new StackPane(circluarPlot);
        pane.setPadding(new Insets(10));

        Scene scene = new Scene(pane);

        stage.setTitle("Circular Plot");
        stage.setScene(scene);
        stage.show();
    }

    @Override public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
