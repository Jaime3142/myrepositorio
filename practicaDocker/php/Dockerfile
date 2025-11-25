FROM php:8.2-apache
RUN docker-php-ext-install mysqli && docker-php-ext-enable mysqli
RUN a2enmod headers \
 && echo '\n<Directory /var/www/html>\nHeader set Access-Control-Allow-Origin "*"\nHeader set Access-Control-Allow-Headers "Content-Type"\nHeader set Access-Control-Allow-Methods "GET, POST, OPTIONS"\n</Directory>\n' >> /etc/apache2/apache2.conf
