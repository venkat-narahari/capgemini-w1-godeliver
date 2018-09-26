FROM node:latest
RUN mkdir -p /GoDeliver
WORKDIR /GoDeliver
COPY package.json /GoDeliver/
RUN npm install --force
RUN npm cache verify
COPY . /GoDeliver
EXPOSE 4200/tcp
CMD ["npm", "start", "--", "--host", "0.0.0.0", "--poll", "2000"]
