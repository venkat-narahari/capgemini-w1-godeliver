FROM node:8.11.4
RUN mkdir -p /GoDeliver
WORKDIR /GoDeliver
COPY package.json /GoDeliver/
RUN ["npm", "install"]
RUN npm cache verify
COPY . /GoDeliver
EXPOSE 4200/tcp
CMD ["npm", "start", "--", "--host", "0.0.0.0", "--poll", "500"]
