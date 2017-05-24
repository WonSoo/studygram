import React, {Component, PropTypes} from 'react';
import TimeAgo from 'react-timeago';

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
            </div>
        );
    }
}
CardHeader.propTypes = propTypes;
CardHeader.defaultProps = defaultProps;
export default CardHeader;
