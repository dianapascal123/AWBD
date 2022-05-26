INSERT INTO `category` (`id`, `title`) VALUES
(1, 'Winter perfumes'),
(2, 'Summer perfumes');

INSERT INTO `product` (title, description, brand, volume, price, year, fragrance_top_notes, image_url, category_id) VALUES
    ('Good Girl', 'Apa de parfum Carolina Herrera Good Girl inspirata din conceptul dualitatii femeii moderne, reflecta contradictii si contraste pentru orice personalitate',
     'Carolina Herrera', '150 ml', 449, 2020,
     'migdale',
     'https://cdn.notinoimg.com/detail_zoom/carolina-herrera/8411061818961x_03/carolina-herrera-good-girl-eau-de-parfum-pentru-femei_.jpg', 1);
