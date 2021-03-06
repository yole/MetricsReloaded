/*
 * Copyright 2005, Sixth and Red River Software
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.sixrr.stockmetrics.packageMetrics;

import com.sixrr.stockmetrics.i18n.HelpURLs;
import com.sixrr.metrics.MetricType;
import com.sixrr.stockmetrics.i18n.StockMetricsBundle;
import org.jetbrains.annotations.Nullable;

public class InstabilityMetric extends PackageMetric {

    public String getDisplayName() {
        return StockMetricsBundle.message("instability.display.name");
    }

    public String getAbbreviation() {
        return StockMetricsBundle.message("instability.abbreviation");
    }

    public MetricType getType() {
        return MetricType.Ratio;
    }

    public String getHelpURL() {
        return HelpURLs.MARTIN_URL;
    }

    @Nullable
    public String getHelpDisplayString() {
        return HelpURLs.MARTIN_DISPLAY_STRING;
    }

    public boolean requiresDependents() {
        return true;
    }
}
