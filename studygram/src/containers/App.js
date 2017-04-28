import React, {Component} from 'react';
/* import logo from './logo.svg'; */
/* import './App.css'; */
import {Header, Card, Contents, PostWriter} from '../components';
import FacebookLogin from 'react-facebook-login';

class App extends Component {
    render() {
        return (
            <div className="App">
                <Header></Header>
                <Contents>
                    <PostWriter></PostWriter>
                    <Card></Card>
                    <Card></Card>
                    <Card></Card>
                    <Card></Card>
                      <FacebookLogin appId="1903051689966506" autoLoad={true} fields="name,email,picture" onClick={() => {
                          console.log("callback");
                      }} callback={(response) => {
                          console.log(response);
                      //    console.log(response.status);
                      }}/>
                </Contents>
                { this.props.children }

                {/* <div className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h2>Welcome to React</h2>
        </div>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p> */}
            </div>
        );
    }
}

export default App;
