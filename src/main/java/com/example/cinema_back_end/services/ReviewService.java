package com.example.cinema_back_end.services;

import com.example.cinema_back_end.dtos.ReviewDTO;
import com.example.cinema_back_end.entities.Review;
import com.example.cinema_back_end.repositories.IMovieRepository;
import com.example.cinema_back_end.repositories.IReviewRepository;
import com.example.cinema_back_end.security.repo.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tritcse00526x
 */
@Service
public class ReviewService implements IReviewService{
    @Autowired
    private IReviewRepository reviewRepository;
    @Autowired
    private IMovieRepository movieRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ModelMapper mapper;

/*START-Override from IReviewService*/
    /**TODO: MOVIE page*/
    /*START - MOVIE detail page - comment session*/
    @Override
    public ReviewDTO add(ReviewDTO r, Integer userId) {
        Review review = new Review();
        review.setComment(r.getComment());
        review.setRating(r.getRating());
        review.setCommentDate(LocalDate.now());
        review.setUser(userRepository.findById(userId).orElse(null));
        review.setMovie(movieRepository.findById(r.getMovieId()).orElse(null));
        return mapper.map(reviewRepository.save(review),ReviewDTO.class);
    }
    /*END - MOVIE detail page - comment session*/
/*END-Override from IReviewService*/

/*START-Override from IGeneralService*/
    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll().stream().map(e -> mapper.map(e, ReviewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getById(Integer id) {
        return mapper.map(reviewRepository.findById(id).get(),ReviewDTO.class);
    }

    @Override
    public void update(ReviewDTO r) {
        Review review = reviewRepository.findById(r.getId()).get();
        review.setComment(r.getComment());
        review.setRating(r.getRating());
        reviewRepository.save(review);
    }

    @Override
    public void remove(Integer id) {
        reviewRepository.deleteById(id);
    }
/*END-Override from IGeneralService*/


    //findById: [CrudRepository interface] expect entity is potentially absent, and want to handle both cases - found or not found - Optional() or empty
    //getById: [JpaRepository  interface] entity is surely exist - if not throw EntityNotFoundException
}
