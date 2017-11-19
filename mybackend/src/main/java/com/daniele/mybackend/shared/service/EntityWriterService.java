package com.daniele.mybackend.shared.service;

import com.daniele.mybackend.shared.model.WriterServiceData;

public interface EntityWriterService<D extends WriterServiceData> {

    void populate(D d);

}
