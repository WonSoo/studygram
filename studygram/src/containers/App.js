import React, { Component } from 'react';
/* import logo from './logo.svg'; */
/* import './App.css'; */
import { Header, Card, Contents } from '../components';

class App extends Component {
  render() {
    return (
      <div className="App">
          <Header></Header>
          <Contents>
              <Card></Card>
          </Contents>


      {  /* <div className="App-header">
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