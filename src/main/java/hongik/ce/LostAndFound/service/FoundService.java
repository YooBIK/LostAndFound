package hongik.ce.LostAndFound.service;

import hongik.ce.LostAndFound.repository.JpaFoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FoundService {
    private JpaFoundRepository jpaFoundRepository;


}
