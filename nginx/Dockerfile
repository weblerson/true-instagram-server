FROM nginx:latest

COPY ./nginx.conf /etc/nginx

RUN mkdir /media/stories
RUN mkdir /media/feeds
RUN mkdir /media/feeds/avatars
RUN mkdir /media/feeds/images

EXPOSE 5173

CMD ["nginx", "-g", "daemon off;"]