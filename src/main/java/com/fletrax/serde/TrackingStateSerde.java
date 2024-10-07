package com.fletrax.serde;

import com.fletrax.model.TrackingState;
import org.apache.kafka.common.serialization.Serdes;

public class TrackingStateSerde extends Serdes.WrapperSerde<TrackingState> {

    public TrackingStateSerde() {
        super(new TrackingStateSerializer(), new TrackingStateSerializer());
    }
}
