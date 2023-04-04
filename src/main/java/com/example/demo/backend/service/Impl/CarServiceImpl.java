package com.example.demo.backend.service.Impl;

import com.example.demo.backend.domain.Car;
import com.example.demo.backend.repository.CarRepository;
import com.example.demo.backend.service.CarService;
import com.example.demo.backend.viewModel.CarViewModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    final CarRepository carRepository;


    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car create(Car car) {
        carRepository.save(car);
        return car;
    }

    @Override
    public List<CarViewModel> getAll() {
        List<Car> carList = carRepository.findAll();
        List<CarViewModel> carViewModels = new ArrayList<>();
        for (Car item : carList) {
            CarViewModel carViewModel = new CarViewModel();
            carViewModel.setId(item.getId());
            carViewModel.setBrand(item.getBrand());
            carViewModel.setModel(item.getModel());
            carViewModel.setRegistrationNumber(item.getRegistrationNumber());
            carViewModels.add(carViewModel);
        }
        return carViewModels;
    }

    @Override
    public List<CarViewModel> getCarsByUserId(long userId) {
        List<CarViewModel> cars = carRepository.getAllCarByUserId(userId);
        return cars;
    }
}