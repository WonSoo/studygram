import React, {Component, PropTypes} from 'react';
import TimeAgo from 'react-timeago';
import Axios from 'axios';
import {Config} from '../../resource';

const propTypes = {
    name: React.PropTypes.string,
    time: React.PropTypes.string
};
const defaultProps = {
    name: "default name momo",
    time: "default time 1분 전"
};
class CardHeader extends Component {
    constructor(props) {
        super(props);
        this.onDeleteClick = this.onDeleteClick.bind(this);
        this.gramDelete = this.gramDelete.bind(this);
    }
    onDeleteClick() {
        this.gramDelete();
    }
    gramDelete() {
        console.log(this.props.cardId);
        Axios({
            method: "delete",
            url: `${Config.ip}/api/gram/${this.props.cardId}`
        }).then((response) => {
        }).catch((error) => {
            console.log(error);
        });
    }
    
    render() {
        return (
            <div className="Card-Header">
                {/* <img src={profilePic} alt="profile"/> */}
                <div className="Card-Header-Name-Div">
                    <h3 className="Card-Header-Name">{this.props.name}</h3>
                </div>
                <div className="Card-Header-Name-Time">
                    <span className="Card-Header-Time"><TimeAgo date={Number(this.props.time)} live={true}/></span>
                </div>
                <button onClick={this.onDeleteClick}>삭제</button>
            </div>
        );
    }
}
CardHeader.propTypes = propTypes;
CardHeader.defaultProps = defaultProps;
export default CardHeader;
