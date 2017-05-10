import React, {Component} from 'react';
import Axios from 'axios';

/* import logo from './logo.svg'; */
/* import './App.css'; */
import {Header, Card, Contents, PostWriter} from '../components';
import FacebookLogin from 'react-facebook-login';
import { Config } from '../resource';

class App extends Component {
    render() {
      console.log(`${Config.ip}`);

        return (
            <div className="App">
                <Header></Header>
                <Contents>
                    <PostWriter></PostWriter>
                    <Card></Card>
                    <Card></Card>
                    <Card></Card>
                    <Card></Card>
                      <FacebookLogin appId={ Config.fbAppId } autoLoad={true} fields="name,email,picture" onClick={() => {
                          console.log("callback");
                      }} callback={(response) => {
                          console.log('callback');
                          console.log(response);
                          if(response.accessToken) {
                              Axios.post(`${ Config.ip }/api/login`, {
                                  accessToken: response.accessToken
                              }).then((response) => {
                                  console.log(response);
                              }).catch((error) => {
                                  console.log(error);
                              });
                          }
                      //    console.log(response.status);
                      }}/>
                </Contents>
            </div>
        );
    }
}

export default App;
