package com.ramazanayyildiz.api.service.impl;

import com.ramazanayyildiz.api.common.GeneralException;
import com.ramazanayyildiz.api.entity.Lecture;
import com.ramazanayyildiz.api.repository.ILectureRepository;
import com.ramazanayyildiz.api.service.ILectureService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureServiceImpl implements ILectureService {

    private final ILectureRepository lectureRepository;

    public LectureServiceImpl(ILectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @Override
    public Lecture save(Lecture lecture) {
        if (StringUtils.isEmpty(lecture.getName())) {
            throw new GeneralException("Name cannot be empty!");
        }
        if (lecture.getTeacher() == null) {
            throw new GeneralException("Teacher cannot be empty!");
        }
        return lectureRepository.save(lecture);
    }

    @Override
    public Lecture getById(Integer id) {
        return lectureRepository.findById(id).orElseThrow(() -> new GeneralException("Lecture not found!"));
    }

    @Override
    public List<Lecture> getAll() {
        return lectureRepository.findAll();
    }

    @Override
    public Page<Lecture> getAll(Pageable pageable) {
        return lectureRepository.findAll(pageable);
    }

    @Override
    public void delete(Integer id) {
        if (!lectureRepository.existsById(id)) {
            throw new GeneralException("Lecture not found!");
        } else{
            lectureRepository.deleteById(id);
        }
    }
}
