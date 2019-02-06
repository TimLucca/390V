(define vecadd
    (lambda (l1 l2)
        (if (null? l1)
            '()
            (cons (+ (car l1) (car l2)) 
                (if (list? (cdr l1))
                    (vecadd (cdr l1) (cdr l2))
                    (vecadd (list (cdr l1)) (list (cdr l2))))))))

(define vecadd
    (lambda (l1 l2)
        (if (null? l1)
            '()
            (if (pair? (cdr l1))
                (cons (+ (car l1) (car l2)) 
                    (vecadd (cdr l1) (cdr l2)))
                (+ (car l1) (car l2))))))
                

-----------------------------------------------------------
Thoughts
-----------------------------------------------------------

> (cons (+ (car l1) (car l2)) (+ (cdr l1) (cdr l2)))    // adds pair ex: (1 . 2) + (1 . 1) = (2 . 3)


======================================================================================
ANSWER
======================================================================================

(define vecadd
    (lambda (l1 l2)
        (if (null? l1)
            '()
            (cons (+ (car l1) (car l2)) 
                (if (list? (cdr l1))
                    (vecadd (cdr l1) (cdr l2))
                    (if(pair? (cdr l1))
                        (vecadd (list (cdr l1)) (list (cdr l2)))
                        (+ (cdr l1) (cdr l2))))))))

======================================================================================