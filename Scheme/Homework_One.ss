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

-----------------------------------------------------------
vecfun
-----------------------------------------------------------







======================================================================================
ANSWER - vecadd
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
ANSWER - vecfn 
======================================================================================

(define vecfn
    (lambda (f l1 l2)
      (if (null? l1)
          '()
          (cons (f (car l1) (car l2))
            (if (list? (cdr l1))
                (vecfn f (cdr l1) (cdr l2))
                (if (pair? (cdr l1))
                    (vecfn f (list (cdr l1)) (list (cdr l2)))
                    (f (cdr l1) (cdr l2))))))))

======================================================================================
ANSWER - vecfun
======================================================================================

(define-syntax vecfun
    ((_ f x y)
        (vecfn f x y)))

======================================================================================
clean
======================================================================================
(define vecfn
    (lambda (f l1 l2)
      (cond
        [(and (null? l1) (null? l2)) '()]
        [(and (pair? l1) (pair? l2)) (cons (f (car l1) (car l2)) (vecfn f (cdr l1) (cdr l2)))]
        [else (f l1 l2)])))



======================================================================================
Different Lengths
======================================================================================

(define vecfn
    (lambda (f l1 l2 base)
        (cond
            [(and (null? l1) (null? l2)) '()]
            [(and (pair? l1) (pair? l2)) (cons (f (car l1) (car l2)) (vecfn f (cdr l1) (cdr l2) base))]
            [(and (pair? l1) (not (pair? l2))) (cons (f (car l1) base) (vecfn f (cdr l1) '() base))]
            [(and (not (pair? l1)) (pair? l2)) (cons (f base (car l2)) (vecfn f '() (cdr l2) base))]
            [else (f l1 l2)])))


======================================================================================
Interesting Observations
======================================================================================

(define-syntax vecfun
    (syntax-rules ()
        ((_ l1 l2)
            (cond
                [(and (null? l1) (null? l2)) '()]))))

> (vecfun '() '())
()

(define-syntax vecfun
    (syntax-rules ()
        ((_ l1 l2)
            (cond
                [(and (null? l1) (null? l2)) '()]
                [(and (pair? l1) (pair? l2)) (cons (+ (car l1) (car l2)) (vecfun (cdr l1) (cdr l2)))]))))

> (vecfun '() '())
*cli crashes*
