(function ($) {
    "use strict"

    // Mobile Nav toggle
    $('.menu-toggle > a').on('click', function (e) {
        e.preventDefault();
        $('#responsive-nav').toggleClass('active');
    })

    // Fix cart dropdown from closing
    $('.cart-dropdown').on('click', function (e) {
        e.stopPropagation();
    });

    /////////////////////////////////////////

    // Products Slick
    $('.products-slick').each(function () {
        var $this = $(this),
            $nav = $this.attr('data-nav');

        $this.slick({
            slidesToShow: 4,
            slidesToScroll: 1,
            autoplay: true,
            infinite: true,
            speed: 300,
            dots: false,
            arrows: true,
            appendArrows: $nav ? $nav : false,
            responsive: [{
                breakpoint: 991,
                settings: {
                    slidesToShow: 2,
                    slidesToScroll: 1,
                }
            },
                {
                    breakpoint: 480,
                    settings: {
                        slidesToShow: 1,
                        slidesToScroll: 1,
                    }
                },
            ]
        });
    });

    let body = $('body')

    body.on('click', '.quick-view', function () {
        let url = "/product/" + $(this).attr("id");
        location.replace(url)
    })

    body.on('click', '.category', function (){
        let value = $(this).attr('id');
        $('#category').val(value);
        $('#categoryDropdown').html(value);
    })

    body.on('click', '.rm-g', function () {
        let csrf = $("meta[name='_csrf']").attr("content");
        let url = "/remove-from-cart";
        let data = "_csrf=" + csrf + "&" + "productId=" + $(this).attr('id').trim();
        callAjax(url, data)
    })

    body.on('click', '.rm-u', function () {
        let csrf = $("meta[name='_csrf']").attr("content");
        let url = "/account/remove-from-cart";
        let data = "_csrf=" + csrf + "&" + "productId=" + $(this).attr('id').trim();
        callAjax(url, data)
    })

    $('.guest-cart').on('click', function () {
        let url = "/add-to-cart";
        let obj = $(this);

        let csrf = $("meta[name='_csrf']").attr("content");
        let data = "_csrf=" + csrf + "&" + "productId=" + obj.attr('id').trim();
        callAjax(url, data)
    })

    $(document).on('click', '.user-cart', function () {
        let url;
        let obj = $(this);
        if (obj.hasClass('rm')) {
            url = "/account/remove-from-cart"
        } else {
            url = "/account/add-to-cart";
        }
        let csrf = $("meta[name='_csrf']").attr("content");
        let data = "_csrf=" + csrf + "&" + "productId=" + obj.attr('id').trim();
        callAjax(url, data)
    })


    function callAjax(url, data) {
        let xhr = $.ajax({
            url: url,
            method: "post",
            data: data,
            complete: function (data) {
                let response = $(data.responseText)

                let cart = $('#cart')
                let newCart = response.find('#cart');
                cart.html(newCart.html());

                let productTableContainer = $('#table-container')
                let newProductTableContainer = response.find(('#table-container'))
                productTableContainer.html(newProductTableContainer.html())

            }

        })
    }

    // Products Widget Slick
    $('.products-widget-slick').each(function () {
        var $this = $(this),
            $nav = $this.attr('data-nav');

        $this.slick({
            infinite: true,
            autoplay: true,
            speed: 300,
            dots: false,
            arrows: true,
            appendArrows: $nav ? $nav : false,
        });
    });

    /////////////////////////////////////////

    // Product Main img Slick
    $('#product-main-img').slick({
        infinite: true,
        speed: 300,
        dots: false,
        arrows: true,
        fade: true,
        asNavFor: '#product-imgs',
    });

    // Product imgs Slick
    $('#product-imgs').slick({
        slidesToShow: 3,
        slidesToScroll: 1,
        arrows: true,
        centerMode: true,
        focusOnSelect: true,
        centerPadding: 0,
        vertical: true,
        asNavFor: '#product-main-img',
        responsive: [{
            breakpoint: 991,
            settings: {
                vertical: false,
                arrows: false,
                dots: true,
            }
        },
        ]
    });

    // Product img zoom
    var zoomMainProduct = document.getElementById('product-main-img');
    if (zoomMainProduct) {
        $('#product-main-img .product-preview').zoom();
    }

    /////////////////////////////////////////

    // Input number
    $('.input-number').each(function () {
        var $this = $(this),
            $input = $this.find('input[type="number"]'),
            up = $this.find('.qty-up'),
            down = $this.find('.qty-down');

        down.on('click', function () {
            var value = parseInt($input.val()) - 1;
            value = value < 1 ? 1 : value;
            $input.val(value);
            $input.change();
            updatePriceSlider($this, value)
        })

        up.on('click', function () {
            var value = parseInt($input.val()) + 1;
            $input.val(value);
            $input.change();
            updatePriceSlider($this, value)
        })
    });

    var priceInputMax = document.getElementById('price-max'),
        priceInputMin = document.getElementById('price-min');

    priceInputMax.addEventListener('change', function () {
        updatePriceSlider($(this).parent(), this.value)
    });

    priceInputMin.addEventListener('change', function () {
        updatePriceSlider($(this).parent(), this.value)
    });

    function updatePriceSlider(elem, value) {
        if (elem.hasClass('price-min')) {
            console.log('min')
            priceSlider.noUiSlider.set([value, null]);
        } else if (elem.hasClass('price-max')) {
            console.log('max')
            priceSlider.noUiSlider.set([null, value]);
        }
    }

    // Price Slider
    var priceSlider = document.getElementById('price-slider');
    if (priceSlider) {
        noUiSlider.create(priceSlider, {
            start: [1, 999],
            connect: true,
            step: 1,
            range: {
                'min': 1,
                'max': 999
            }
        });

        priceSlider.noUiSlider.on('update', function (values, handle) {
            var value = values[handle];
            handle ? priceInputMax.value = value : priceInputMin.value = value
        });
    }


})(jQuery);
