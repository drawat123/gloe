package io.gloe.gloe.service;

import io.gloe.gloe.entity.Source;
import io.gloe.gloe.repository.SourceRepository;
import org.springframework.stereotype.Service;

@Service
public class SourceServiceImpl implements SourceService {
    private final SourceRepository sourceRepository;

    public SourceServiceImpl(SourceRepository sourceRepository) {
        this.sourceRepository = sourceRepository;
    }

    @Override
    public Source createSource(Source source) {
        return sourceRepository.save(source);
    }
}
