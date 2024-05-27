package com.berijalan.technicaltest.controller;

import com.berijalan.technicaltest.entity.Buah;
import com.berijalan.technicaltest.exception.NotFoundException;
import com.berijalan.technicaltest.repository.BuahRepository;
import com.berijalan.technicaltest.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BuahController {

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


    // table buah
    @GetMapping("buah")
    public String getAll(Model model) {

        try{
            List<Buah> listBuah = new ArrayList<>(buahRepository.findAllBuah());

            model.addAttribute("buah", listBuah);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "buah";
    }

    // halaman add
    @GetMapping(value = "/buah/new", produces = "application/json")
    public String addBuah(Model model) {

        Buah buah = new Buah();

        model.addAttribute("buah", buah);
        model.addAttribute("pageTitle", "Add new Buah");

        return "buah_form";
    }

    // save buah
    @PostMapping("/buah/save")
    public String saveBuah(Buah buah, RedirectAttributes redirectAttributes) {

        try {

            buahRepository.save(buah);

            redirectAttributes.addFlashAttribute("message", "Data buah berhasil disimpan!");

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/buah";
    }

    // update
    @GetMapping("/buah/{id}")
    public String updateBuah(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {

        try {

            Buah buah = buahRepository.findById(id).get();

            model.addAttribute("buah", buah);
            model.addAttribute("pageTitle", "Update buah dengan ID: " + id);

            return "buah_form";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/buah";
        }
    }

    @GetMapping("/buah/delete/{id}")
    public String deleteBuah(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {

        try {

            buahRepository.deleteBuahById(id);

            redirectAttributes.addFlashAttribute("message", "Buah dengan ID: " + id + " berhasil dihapus!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/buah";
    }
}
