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


public class DemoTravel extends Application {
    private CircularPlot circluarPlot;

    @Override public void init() {
        // Setup Data
        PlotItem australia = new PlotItem("AUSTRALIA", 1_250_000, Color.rgb(255, 51, 51));
        PlotItem india     = new PlotItem("INDIA", 750_000, Color.rgb(255, 153, 51));
        PlotItem china     = new PlotItem("CHINA", 920_000, Color.rgb(255, 255, 51));
        PlotItem japan     = new PlotItem("JAPAN", 1_060_000, Color.rgb(153, 255, 51));
        PlotItem thailand  = new PlotItem("THAILAND", 720_000, Color.rgb(51, 255, 51));
        PlotItem singapore = new PlotItem("SINGAPORE", 800_000, Color.rgb(51, 255, 153));

        // Travel flow from Australia to other countries
        australia.addToOutgoing(india, 150000);
        australia.addToOutgoing(china, 90000);
        australia.addToOutgoing(japan, 180000);
        australia.addToOutgoing(thailand, 15000);
        australia.addToOutgoing(singapore, 10000);

        // Travel flow from India to other countries
        india.addToOutgoing(australia, 35000);
        india.addToOutgoing(china, 10000);
        india.addToOutgoing(japan, 40000);
        india.addToOutgoing(thailand, 25000);
        india.addToOutgoing(singapore, 8000);

        // Travel flow from China to other countries
        china.addToOutgoing(australia, 10000);
        china.addToOutgoing(india, 7000);
        china.addToOutgoing(japan, 40000);
        china.addToOutgoing(thailand, 5000);
        china.addToOutgoing(singapore, 4000);

        // Travel flow from Japan to other countries
        japan.addToOutgoing(australia, 7000);
        japan.addToOutgoing(india, 8000);
        japan.addToOutgoing(china, 175000);
        japan.addToOutgoing(thailand, 11000);
        japan.addToOutgoing(singapore, 18000);

        // Travel flow from Thailand to other countries
        thailand.addToOutgoing(australia, 70000);
        thailand.addToOutgoing(india, 30000);
        thailand.addToOutgoing(china, 22000);
        thailand.addToOutgoing(japan, 120000);
        thailand.addToOutgoing(singapore, 40000);

        // Travel flow from Singapore to other countries
        singapore.addToOutgoing(australia, 60000);
        singapore.addToOutgoing(india, 90000);
        singapore.addToOutgoing(china, 110000);
        singapore.addToOutgoing(japan, 14000);
        singapore.addToOutgoing(thailand, 30000);

        // Setup Chart
        circluarPlot = CircularPlotBuilder.create()
                                          .prefSize(500, 500)
                                          .items(australia, india, china, japan, thailand, singapore)
                                          .connectionOpacity(0.75)
                                          .showFlowDirection(true)
                                          .minorTickMarksVisible(false)
                                          .onlyFirstAndLastTickLabelVisible(true)
                                          .decimals(0)
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
