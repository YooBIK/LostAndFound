package hongik.ce.LostAndFound.service;

import hongik.ce.LostAndFound.repository.LostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LostService {

    private final LostRepository lostRepository;
}
