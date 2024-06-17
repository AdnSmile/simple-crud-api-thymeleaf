package com.berijalan.technicaltest.controller;

import com.berijalan.technicaltest.entity.Buah;
import com.berijalan.technicaltest.exception.NotFoundException;
import com.berijalan.technicaltest.repository.BuahRepository;
import com.berijalan.technicaltest.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ApiBuahController {


    @Autowired
    private BuahRepository buahRepository;

    // list api buah
    @GetMapping("/api/buah")
    @ResponseBody
    public ResponseEntity<ApiResponse<List<Buah>>> getAllBuah() {

        try {
            List<Buah> listBuah = new ArrayList<>(buahRepository.findAllBuah());

            ApiResponse<List<Buah>> apiResponse = new ApiResponse<>("List buah", listBuah);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<List<Buah>> apiResponse = new ApiResponse<>(e.getMessage(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // create api buah
    @PostMapping(value = "/api/buah", produces ="application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<Buah>> saveApiBuah(@RequestBody Buah body) {

        try{

            Buah response = buahRepository.save(body);

            ApiResponse<Buah> apiResponse = new ApiResponse<>("Buah berhasil disimpan!", response);

            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            ApiResponse<Buah> apiResponse = new ApiResponse<>(e.getMessage(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // api update
    @PutMapping(value = "/api/buah/{id}", produces ="application/json", consumes = "application/json")
    public ResponseEntity<ApiResponse<Buah>> updateApiBuah(@PathVariable("id") Integer id, @RequestBody Buah body) {

        try{

            Buah findBuah = buahRepository.findById(id).orElseThrow(() -> new NotFoundException("Buah tidak ditemukan"));

            findBuah.setNama(body.getNama());
            findBuah.setWarna(body.getWarna());
            findBuah.setDeskripsi(body.getDeskripsi());
            findBuah.setAsal(body.getAsal());
            findBuah.setBerat(body.getBerat());

            Buah response = buahRepository.save(findBuah);

            ApiResponse<Buah> apiResponse = new ApiResponse<>("Buah berhasil diubah", response);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<Buah> apiResponse = new ApiResponse<>(e.getMessage(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // delete api buah
    @DeleteMapping(value = "/api/buah/{id}", produces = "application/json")
    public ResponseEntity<ApiResponse<Buah>> deleteBuah(@PathVariable("id") Integer id) {

        try {
            Buah findBuah = buahRepository.findById(id).orElseThrow(() -> new NotFoundException("Buah tidak ditemukan"));

            buahRepository.deleteBuahById(id);


            ApiResponse<Buah> apiResponse = new ApiResponse<>("Buah berhasil dihapus!", findBuah);

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse<Buah> apiResponse = new ApiResponse<>(e.getMessage(), null);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
