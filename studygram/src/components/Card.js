import React, { Component, PropTypes } from 'react';
import CardHeader from './CardHeader';
import CardPicture from './CardPicture';
import CardTagContainer from './CardTagContainer';
import CardArticle from './CardArticle';
import sanaPic from '../img/sana.jpg'


const propTypes = {
};
const defaultProps = {
};
class Card extends Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "momo",
            time: "1분 전",
            picture: sanaPic,
            content: "나는 아니야",
            title: "밤이되면 내맘속의 출입문이",
            tags: ["momo", "twice", "knock knock"]
        }
    }
    render() {
        return(
            <div className="Card">
                <CardHeader name={this.state.name} time={this.state.time}/>
                <CardPicture picture={this.state.picture}/>
                <CardTagContainer tags={this.state.tags}/>
                <CardArticle content={this.state.content} title={this.state.title}/>
            </div>
        );
    }
}
Card.propTypes = propTypes;
Card.defaultProps = defaultProps;
export default Card;
