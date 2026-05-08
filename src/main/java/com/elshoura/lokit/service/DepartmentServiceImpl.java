package com.elshoura.lokit.service;

import com.elshoura.lokit.errors.exception.AlreadyExistException;
import com.elshoura.lokit.errors.exception.NotFoundException;
import com.elshoura.lokit.models.dto.request.DepartmentRequest;
import com.elshoura.lokit.models.dto.response.DepartmentResponse;
import com.elshoura.lokit.models.entitys.Department;
import com.elshoura.lokit.repository.DepartmentRepository;
import com.elshoura.lokit.utils.mapper.DepartmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.elshoura.lokit.utils.mapper.DepartmentMapper.toDepartment;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

      @Override
      public DepartmentResponse addDepartment(DepartmentRequest departmentRequest){

      String name = departmentRequest.name().trim();

                departmentRepository.findByNameIgnoreCase(name)
                      .ifPresent(d -> {throw new AlreadyExistException("department  already exists");});

               Department department = toDepartment(departmentRequest);

              Department savedDepartment = departmentRepository.save(department);

              return DepartmentMapper.toDepartmentResponse(savedDepartment);

  }
    @Override
    public DepartmentResponse updateDepartment(Long id,DepartmentRequest departmentRequest){

       Department department= departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("department not found"));

        String name = departmentRequest.name().trim();

        departmentRepository.findByNameIgnoreCase(name)
                .filter(d -> department.getId().equals(id))
                .ifPresent(d -> {throw new AlreadyExistException("department already exists");
                });

        department.setName(departmentRequest.name());
        Department savedDepartment = departmentRepository.save(department);

        return DepartmentMapper.toDepartmentResponse(savedDepartment);

    }
    @Override
   public DepartmentResponse getDepartmentById(Long id){

       Department department= departmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("department not found"));

        return DepartmentMapper.toDepartmentResponse(department);
    }
    @Override
   public List<DepartmentResponse> getAllDepartments(){

       return departmentRepository.findAllByOrderByIdAsc().stream()
               .map(DepartmentMapper::toDepartmentResponse)
               .toList();

   }
   @Override
   public void deleteDepartment(Long id){

       departmentRepository.findById(id).orElseThrow(()->new NotFoundException("department not found"));

       departmentRepository.deleteById(id);

   }

}
